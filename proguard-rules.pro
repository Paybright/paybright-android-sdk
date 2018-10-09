#
# This ProGuard configuration file illustrates how to process a program
# library, such that it remains usable as a library.


# Specify the input jars, output jars, and library jars.
# In this case, the input jar is the program library that we want to process.

# Save the obfuscation mapping to a file, so we can de-obfuscate any stack
# traces later on. Keep a fixed source file attribute and all line number
# tables to get line numbers in the stack traces.
# You can comment this out if you're not interested in stack traces.

-keepparameternames
-renamesourcefileattribute SourceFile

# Preserve all annotations.

# Preserve all public classes, and their public and protected fields and
# methods.

-keep public class * {
   public *;
}

-keep public enum com.paybright.sdk.**{
    *;
}

# Preserve all .class method names.


# Preserve all native method names and the names of their classes.



# Preserve the special static methods that are required in all enumeration
# classes.


# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your library doesn't use serialization.
# If your code contains serializable classes that have to be backward
# compatible, please refer to the manual.

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Your library may contain more items that need to be preserved;
# typically classes that are dynamically created using Class.forName:

# -keep public class mypackage.MyClass
# -keep public interface mypackage.MyInterface
# -keep public class * implements mypackage.MyInterface


-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**
-dontwarn android.net.**