package com.example.demo;

import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: chenr
 * @date: 2017/8/10
 */
public class Test {


    public String reverse(String originalStr) {
        // 收敛条件
        if (StringUtils.isEmpty(originalStr) || originalStr.length() <= 1) {
            return originalStr;
        }
        // 递归公式
        return reverse(originalStr.substring(1)) + originalStr.charAt(0);
    }


    /*String对象的intern方法会得到字符串对象在常量池中对应的版本的引用（如果常量池中有一个字符串与String对象的equals结果是true），
        如果常量池中没有对应的字符串，则该字符串将被添加到常量池中，然后返回常量池中字符串的引用。*/

    @org.junit.Test
    public void main() {
        String str1 = new String("asdf") + "123";
        String str2 = "asdf123";
        String str3 = new StringBuilder("asdf").append("123").toString();
        String str4 = "asdf" + "123";
        StringBuilder sb = new StringBuilder();
        String str5 = sb.append("asdf").append("123").toString();
        System.out.println(str4 == str5.intern());

        // System.out.println(this.reverse("\uD835\uDD6Babcd"));
        // System.out.println("\\uD835\\uDD6Babcd".length());

        // List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        // features.forEach(System.out::println);

        // List costBeforeTax = Arrays.asList(100, 200, 300, 400);
        // costBeforeTax.stream().map((c) -> (Double)c + .12*(Double)c).forEach(System.out::println);
        // costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, c) -> sum + c).get();

      /*  List<Person> personList = Lists.newArrayList();
        personList.add(new Person("1"));
        personList.add(new Person("2"));
        personList.add(new Person("3"));

        List<Person> judgeList = Lists.newArrayList();
        Person judgePerson = null;
        for (Person person : personList) {
            judgePerson = person;
            judgeList.add(judgePerson);
        }

        for (Person print : judgeList) {

            System.out.println(print.getName());
        }*/

    }

    /*
     在Java 5以前，switch(expr)中，expr只能是byte、short、char、int。从Java 5开始，Java中引入了枚举类型，expr也可以是enum类型，从Java 7开始，expr还可以是字符串（String），但是长整型（long）在目前所有的版本中都是不可以的。
如果需要定义变量控制循环次数。建议使用for。因为for循环完毕，变量在内存中释放。
如何区分重载：当函数同名时，只看参数列表。和返回值类型没关系。
压栈-进栈
静态内部类
Math.round(11.5)的返回值是12，Math.round(-11.5)的返回值是-11。四舍五入的原理是在参数上加0.5然后进行下取整。
数组没有length()方法，有length 的属性。String 有length()方法。JavaScript中，获得字符串的长度是通过length属性得到的，这一点容易和Java混淆。
构造器不能被继承，因此不能被重写，但可以被重载
字符串的+操作其本质是创建了StringBuilder对象进行append操作，然后将拼接后的StringBuilder对象用toString方法处理成String对象

方法的重载和重写都是实现多态的方式，区别在于前者实现的是编译时的多态性，而后者实现的是运行时的多态性。

21、描述一下JVM加载class文件的原理机制？
答：JVM中类的装载是由类加载器（ClassLoader）和它的子类来实现的，Java中的类加载器是一个重要的Java运行时系统组件，它负责在运行时查找和装入类文件中的类。
由于Java的跨平台性，经过编译的Java源程序并不是一个可执行程序，而是一个或多个类文件。当Java程序需要使用某个类时，JVM会确保这个类已经被加载、连接（验证、准备和解析）和初始化。类的加载是指把类的.class文件中的数据读入到内存中，通常是创建一个字节数组读入.class文件，然后产生与所加载类对应的Class对象。加载完成后，Class对象还不完整，所以此时的类还不可用。当类被加载后就进入连接阶段，这一阶段包括验证、准备（为静态变量分配内存并设置默认的初始值）和解析（将符号引用替换为直接引用）三个步骤。最后JVM对类进行初始化，包括：1)如果类存在直接的父类并且这个类还没有被初始化，那么就先初始化父类；2)如果类中存在初始化语句，就依次执行这些初始化语句。
类的加载是由类加载器完成的，类加载器包括：根加载器（BootStrap）、扩展加载器（Extension）、系统加载器（System）和用户自定义类加载器（java.lang.ClassLoader的子类）。从Java 2（JDK 1.2）开始，类加载过程采取了父亲委托机制（PDM）。PDM更好的保证了Java平台的安全性，在该机制中，JVM自带的Bootstrap是根加载器，其他的加载器都有且仅有一个父类加载器。类的加载首先请求父类加载器加载，父类加载器无能为力时才由其子类加载器自行加载。JVM不会向Java程序提供对Bootstrap的引用。下面是关于几个类加载器的说明：

	* Bootstrap：一般用本地代码实现，负责加载JVM基础核心类库（rt.jar）；
	* Extension：从java.ext.dirs系统属性所指定的目录中加载类库，它的父加载器是Bootstrap；
	* System：又叫应用类加载器，其父类是Extension。它是应用最广泛的类加载器。它从环境变量classpath或者系统属性java.class.path所指定的目录中记载类，是用户自定义加载器的默认父加载器。




在JVM内部都是Unicode，当这个字符被从JVM内部转移到外部时（例如存入文件系统中），需要进行编码转换。所以Java中有字节流和字符流

静态方法中没有this，也就是说没有所谓的外部类对象，因此无法创建内部类对象，如果要在静态方法中创建内部类对象，可以这样做：
    new Outer().new Inner();


因为栈内部维护着对这些对象的过期引用（obsolete reference）。不会被回收，造成内存泄露
在支持垃圾回收的语言中，内存泄露是很隐蔽的，这种内存泄露其实就是无意识的对象保持。如果一个对象引用被无意识的保留起来了，那么垃圾回收器不会处理这个对象

然而时过境迁，如今Java的垃圾回收机制已经成为被诟病的东西。移动智能终端用户通常觉得iOS的系统比Android系统有更好的用户体验，其中一个深层次的原因就在于Android系统中垃圾回收的不可预知性。 然而时过境迁，如今Java的垃圾回收机制已经成为被诟病的东西。移动智能终端用户通常觉得iOS的系统比Android系统有更好的用户体验，其中一个深层次的原因就在于Android系统中垃圾回收的不可预知性。

垃圾回收机制有很多种，包括：分代复制垃圾回收、标记垃圾回收、增量垃圾回收等方式。标准的Java进程既有栈又有堆。栈保存了原始型局部变量，堆保存了要创建的对象。Java平台对堆内存回收和再利用的基本算法被称为标记和清除，但是Java对其进行了改进，采用“分代式垃圾收集”。这种方法会跟Java对象的生命周期将堆内存划分为不同的区域，在垃圾收集过程中，可能会将对象移动到不同区域：
- 伊甸园（Eden）：这是对象最初诞生的区域，并且对大多数对象来说，这里是它们唯一存在过的区域。
- 幸存者乐园（Survivor）：从伊甸园幸存下来的对象会被挪到这里。
- 终身颐养园（Tenured）：这是足够老的幸存对象的归宿。年轻代收集（Minor-GC）过程是不会触及这个地方的。当年轻代收集不能把对象放进终身颐养园时，就会触发一次完全收集（Major-GC），这里可能还会牵扯到压缩，以便为大对象腾出足够的空间。

一个内部类对象可以访问创建它的外部类对象的成员，包括私有成员。

创建对象时构造器的调用顺序是：先初始化静态成员，然后调用父类构造器，再初始化非静态成员，最后调用自身构造器。

递归

39、如何实现字符串的反转及替换？
答：方法很多，可以自己写实现也可以使用String或StringBuffer/StringBuilder中的方法。有一道很常见的面试题是用递归实现字符串反转，代码如下所示：
    public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1)
            return originStr;
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

怎样将GB2312编码的字符串转换为ISO-8859-1编码的字符串？
答：代码如下所示：
String s1 = "你好";
String s2 = new String(s1.getBytes("GB2312"), "ISO-8859-1");

Java的时间日期API一直以来都是被诟病的东西，为了解决这一问题，Java 8中引入了新的时间日期API，其中包括LocalDate、LocalTime、LocalDateTime、Clock、Instant等类，这些的类的设计都使用了不变模式，因此是线程安全的设计。如果不理解这些内容，可以参考我的另一篇文章《关于Java并发编程的总结和思考》。

用递归编写程序时一定要牢记两点：1. 递归公式；2. 收敛条件（什么时候就不再继续递归）。

在finally中改变返回值的做法是不好的，因为如果存在finally代码块，try中的return语句不会立马返回调用者，而是记录下返回值待finally代码块执行完毕之后再向调用者返回其值，然后如果在finally中修改了返回值，就会返回修改后的值。显然，在finally中返回或者修改返回值会对程序造成很大的困扰，C#中直接用编译错误的方式来阻止程序员干这种龌龊的事情，Java中也可以通过提升编译器的语法检查级别来产生警告或错误，Eclipse中可以在如图所示的地方进行设置，强烈建议将此项设置为编译错误。

因为非静态方法上的synchronized修饰符要求执行方法时要获得对象的锁

泛型

java工具类

     */


    class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
