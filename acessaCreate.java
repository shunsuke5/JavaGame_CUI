public class acessaCreate {
    public static void createGetterString(String member) {
        String upperMember = member.substring(0,1).toUpperCase() + 
                member.substring(1);
        System.out.println("public String get" + upperMember + "() { return this." + member + "; }");
    }
    public static void createGetterInt(String member) {
        String upperMember = member.substring(0,1).toUpperCase() + 
                member.substring(1);
        System.out.println("public int get" + upperMember + "() { return this." + member + "; }");
    }
    public static void createSetterString(String member) {
        String upperMember = member.substring(0,1).toUpperCase() + 
                member.substring(1);
        System.out.println("public void set" + upperMember + "(String " + member + ") { this." + member + " = " + member + "; }");
    }
    public static void createSetterInt(String member) {
        String upperMember = member.substring(0,1).toUpperCase() + 
                member.substring(1);
        System.out.println("public void set" + upperMember + "(int " + member + ") { this." + member + " = " + member + "; }");
    }
}