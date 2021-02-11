public class main {
    public static void main(String [] args)
    {
        GenericBSTClass<StudentClass> test = new GenericBSTClass();
        StudentClass clay = new StudentClass("clay", 4,'a', 3);
        StudentClass quinn = new StudentClass("quinn", 2,'l', 1);
        StudentClass buddy = new StudentClass("buddy", 3,'z', 2);
        StudentClass jess = new StudentClass("jess", 1,'z', 2);
        StudentClass.setSortKey(1);
        test.insertData(buddy);
        test.insertData(clay);

        test.insertData(quinn);
        test.insertData(jess);

        test.displayTree(101);
        System.out.println(test.search(jess));
    }
}
