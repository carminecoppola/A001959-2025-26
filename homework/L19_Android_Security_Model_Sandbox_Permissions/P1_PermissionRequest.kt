// L19 - P1: richiesta di permessi nell'app Android.
// Questo esempio mostra il flusso concettuale: verificare, richiedere e registrare il permesso.

class PermissionRequestSimulatorP1 {
    private val grantedPermissions = mutableSetOf<String>()

    fun isGranted(permission: String): Boolean {
        return permission in grantedPermissions
    }

    fun request(permission: String): String {
        return if (isGranted(permission)) {
            "Permesso gia concesso: $permission"
        } else {
            grantedPermissions.add(permission)
            "Permesso richiesto e concesso: $permission"
        }
    }

    fun revoke(permission: String): String {
        val removed = grantedPermissions.remove(permission)
        return if (removed) {
            "Permesso revocato: $permission"
        } else {
            "Permesso non presente: $permission"
        }
    }
}

// Caso d'uso di base: richiediamo un permesso e poi lo controlliamo.
fun demoL19P1PermissionRequest(): List<String> {
    val simulator = PermissionRequestSimulatorP1()
    return listOf(
        simulator.request("android.permission.CAMERA"),
        simulator.request("android.permission.CAMERA"),
        simulator.revoke("android.permission.CAMERA")
    )
}