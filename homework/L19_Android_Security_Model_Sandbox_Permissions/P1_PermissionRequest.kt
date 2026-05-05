// L19 - P1: richiesta di permessi nell'app Android.
// This example shows the flow concettuale: verify, request and registrare the permission.

class PermissionRequestSimulatorP1 {
    private val grantedPermissions = mutableSetOf<String>()

    fun isGranted(permission: String): Boolean {
        return permission in grantedPermissions
    }

    fun request(permission: String): String {
        return if (isGranted(permission)) {
            "Permission already granted: $permission"
        } else {
            grantedPermissions.add(permission)
            "Permission requested and granted: $permission"
        }
    }

    fun revoke(permission: String): String {
        val removed = grantedPermissions.remove(permission)
        return if (removed) {
            "Permesso revocato: $permission"
        } else {
            "Permission not present: $permission"
        }
    }
}

// Basic use case: we request a permission and then verify it.
fun demoL19P1PermissionRequest(): List<String> {
    val simulator = PermissionRequestSimulatorP1()
    return listOf(
        simulator.request("android.permission.CAMERA"),
        simulator.request("android.permission.CAMERA"),
        simulator.revoke("android.permission.CAMERA")
    )
}

fun main() {
    val results = demoL19P1PermissionRequest()
    results.forEach { println(it) }
}