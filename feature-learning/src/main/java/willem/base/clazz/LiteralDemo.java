package willem.base.clazz;

/**
 * @Author weiyu
 * @Description 字面量新写法，如1_00即表示100，编译器会自动忽略下划线
 * @Date 2019/3/6 18:01
 * @since jdk1.7
 */
public class LiteralDemo {

    public static void main(String[] args) {
        int var1 = 1_00;
        System.out.println("******var1="+var1);

        int var2 = 11_1000;
        System.out.println("******var2="+var2);

        System.out.println("******var2/var1="+var2/var1);
    }
}
