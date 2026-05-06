

# Keep data model fields if they are used by reflection-based serializers.
-keepclassmembers class com.example.l24securereleasechecklist.ReleaseCheck {
    <fields>;
}

# Keep enum values, useful when enums are serialized or deserialized.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Suppress known harmless warnings for this small demo project.
-dontwarn kotlin.**