from flask import Flask, request, jsonify
from flask_cors import CORS
import codicefiscale
from datetime import datetime

app = Flask(__name__)
CORS(app)

@app.route('/api/v1/fiscal-code', methods=['POST'])
def compute_fiscal_code():
    data = request.get_json(force=True)
    if data is None:
        return jsonify({"error": "Request body must be JSON"}), 400

    required = ['surname', 'name', 'birthdate', 'sex', 'birthplace']
    missing = [f for f in required if not data.get(f)]
    if missing:
        return jsonify({"error": f"Missing fields: {missing}"}), 400

    sex = data['sex'].upper()
    if sex not in ('M', 'F'):
        return jsonify({"error": "sex must be M or F"}), 400

    try:
        birthdate = datetime.strptime(data['birthdate'], '%Y-%m-%d')
        cf = codicefiscale.build(
            data['surname'],
            data['name'],
            sex,
            birthdate,
            data['birthplace']
        )
        return jsonify({"fiscal_code": cf, "input": data})
    except ValueError as e:
        return jsonify({"error": str(e)}), 422
    except Exception as e:
        import traceback
        traceback.print_exc()
        return jsonify({"error": str(e)}), 500

@app.route('/health')
def health():
    return jsonify({"status": "ok"})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080, debug=True)