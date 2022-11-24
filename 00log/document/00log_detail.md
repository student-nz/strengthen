# 一、加强篇(日志篇)

# 1. 日志文件

## 	1. 1 什么是日志文件

​	日志文件就是用于记录系统操作事件的记录文件或文件集合，可分为事件日志和消息日志

**1. 事件日志**

​	事‎件日志就是专‎门用来记录计算‎机硬件、软‎件和系统整‎体方面的错‎误信息,也‎记录一些安‎全方面的问‎题

**2. 消息日志**

​	消息日志存储消息中心所有业务推送的各个渠道为单位的消息日志

​	消息日志**默认**只保留渠道消息**失败**日志，用户可以通过打开所需渠道上的**启用日志**开关，允许消息中心记录该渠道**完整**日志

### 	1.1.1 调试日志

​	软件开发中，我们经常需要去调试程序，做一些信息，状态的输出便于我们查询程序的运行状况，

​	调试也就是debug 可以在程序运行中暂停程序运行，可以查看程序在运行中的情况，

​	调试日志主要是为了更方便的去重现问题

### 1.1.2 系统日志

​	系统日志是记录系统中硬件、软件和系统问题的信息，同时还可以监视系统中发生的事件！

​	用户可以通过它来检查错误发生的原因，或者寻找受到攻击时攻击者留下的痕迹！

​	系统日志包括系统日志、应用程序日志和安全日志，由此看出系统日志其实包括三部分！

​	**系统日志的价值**

​		系统日志策略可以在故障刚刚发生时就向你发送警告信息，系统日志可以帮助你在最短的时间内发现问题	

# 2.  JAVA日志框架

## 2.1 为什么要用日志框架

​		因为软件系统发展到今天已经很复杂,特别是服务器端软件,涉及到的知识、内容、问题太多，

​		在某方面使用别人成熟的框架完成一些基础操作，节省了大量的精力和时间投入！

## 2.2  现有的日志框架

​	JUL（java util logging）、logback、log4j、log4j2
​	JCL（Jakarta Commons Logging）、slf4j（ Simple Logging Facade for Java）

## 2.3 日志门面

### 2.3.1 什么是日志门面？

​	日志门面就是在日志框架和应用程序之间架设一个沟通的桥梁，

​	对于应用程序来说，无论底层的日志框架如何变，都不需要有任何感知

​	简单理解：日志门面就是java中的一个interface（接口），而日志框架就是就是实现类

### 2.3.2 常见日志门面

​		JCL、slf4j

### 2.3.3 常见日志实现

​		JUL、logback、log4j、log4j2

# 3. 日志实现

## 3.1. JUL

### 3.1.1 什么是JUL

​	首先**JUL**全称**Java util Logging**，它是java原生的日志框架，使用时不需要另外引用第三方类库，

​	相对其他日志框架使用方便，学习简单，能够在小型应用中灵活使用

### 	3.1.2 JUL架构

​	![image-20221020103156074](assets\image-20221020103156074.png)

**1. Loggers**

​	Loggers被称为记录器，应用程序通过获取Logger对象，调用其API来发布日志信息，

​	也就是说Logger通常是应用程序访问日志系统的入口程序！

**2. Appenders**

​	Appenders也被称为Handlers，每个Logger都会关联一组Handlers，

​	Logger会将日志交给关联Handlers处理，由Handlers负责将日志做记录！

​	Handlers是一个抽象，其具体的实现决定了日志记录的位置可以是控制台、文件、网络上的其他日志服务或操作系统日志等

**3. Layouts**

​	Layouts也被称为Formatters，负责对日志事件中的数据进行转换和格式化，

​	Layouts决定了数据在一条日志记录中的最终形式

**4. Level**

​	每条日志消息都有一个关联的日志级别，

​	日志级别粗略指导了日志消息的重要性和紧迫，

​	我可以将Level和Loggers通过Appenders做关联以便于我们过滤消息！

**5. Filters**

​	相信大家并不陌生，本人Web核心技术阶段就整理过 过滤器 Filters

​	这里的过滤器就是 根据需要：定制哪些信息会被记录，哪些信息会被放过！

**整理可知具体详细流程如下：**

​	**步骤一：**用户使用Logger来进行日志记录，
​	**步骤二：**Logger持有若干个Handler，日志的输出操作是由Handler完成的
​	**步骤三：**在Handler处理器输出日志前，会经过Filter过滤器的过滤，判断哪些日志级别过滤放行哪些拦截，
​	**步骤四：**在Handler处理器会将日志内容输出到指定位置（日志文件、控制台、网络上的其他日志服务或操作系统日志等等）
​	**步骤五：**在Handler在输出日志时会使用Layout，将输出内容进行排版，即格式化所需形式

**测试案例**

```
    // 快速入门
    @Test
    public void testQuick()throws Exception{
        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger("com.yj.nz.JULTest");
        // 2.日志记录输出
        logger.info("hello jul");

        // 通用方法进行日志记录
        logger.log(Level.INFO,"info msg");


        // 通过占位符 方式输出变量值
        String name = "yjxz";
        Integer age = 13;
        logger.log(Level.INFO,"用户信息：{0},{1}",new Object[]{name,age});

    }
```

### 3.1.3 日志级别

#### 1.  JUL日志级别：

​		JUL日志级别由类java.util.logging.Level记录，总共有七个日志级别，由高到低分别是：

​		**1. SEVERE（最高值）**错误信息，一般用来记录导致系统终止的信息

​		**2. WARNING** 警告信息，一般用来记录程序的问题，该问题不会导致系统终止！

​		**3. INFO（默认级别）**一般信息，一般用来记录一些连接信息，访问信息等

​		**4. CONFIG** 配置信息，一般用来记录加载配置文件等日志信息

​		**5. FINE**  Debug日志信息，一般用来记录程序一般运行的状态，执行的流程参数的传递等信息

​		**6. FINER** FINER 与 FINE  类似，只不过记录的颗粒度更高一些 

​		**7. FINEST**  与以上两个类似，只不过记录的颗粒度更高一些，即 FINEST：最低值

​		所谓**颗粒度大**表示宏观，**颗粒度小**表示更微观、注重细节

#### 2. 两个特殊的级别：

​		**1. OFF** 用来关闭日志记录

​		**2. ALL** 启用所有消息的日志记录

#### 3. 日志级别输出

​		假设：日志级别设置为L,一个级别为P的输出日志只有当P >= L时日志才会输出

**测试案例**

```
    // 日志级别
    @Test
    public void testLogLevel()throws Exception{
        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger("com.yj.nz.JULTest");
        // 2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info"); // 默认日志输出级别
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");

    }
```

**自定义日志级别**

```
  // 自定义日志级别
    @Test
    public void testLogConfig()throws Exception{
        // 1.获取日志记录器对象
        Logger logger = Logger.getLogger("com.yj.nz.JULTest");


        // 关闭系统默认配置
        logger.setUseParentHandlers(false);

        // 自定义配置日志级别
        // 创建ConsolHhandler 控制台输出
        ConsoleHandler consoleHandler = new ConsoleHandler();

        // 创建简单格式转换对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();

        // 进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);


        // 配置日志具体级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);


        // 场景FileHandler  文件输出
        FileHandler fileHandler = new FileHandler("D:/logs/jul.log");

        // 进行关联
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);

        // 2.日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info"); // 默认日志输出级别
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");

    }
```

### 3.3.4 Logger之间的父子关系

​		JUL中Logger之间存在父子关系，这种父子关系通过树状结构存储，

​		JUL在初始化时会创建一个顶层RootLogger作为所有Logger的父Logger，

​		存储上作为树状结构的根节点，并父子关系通过路径来关联,

​		父亲所做的设置，也能够同时作用于儿子，

​		例如：**com.yj.nz**所做的设置，会作用于**com.yj.nz.log**，简单就是说能作用于**com.yj.nz**包下的子目录或文件！

**测试案例**

```
    // Logger对象父子关系
    @Test
    public void testLogParent()throws Exception{

        Logger logger1 = Logger.getLogger("com.yj.nz.log");
        Logger logger2 = Logger.getLogger("com.yj.nz");

        // 测试
        System.out.println(logger1.getParent() == logger2);
        // 所有日志记录器的顶级父元素 LogManager$RootLogger，name ""
        System.out.println("logger2 Parent:"+logger2.getParent() + ",name:" + logger2.getParent().getName());

        // 关闭默认配置
        logger2.setUseParentHandlers(false);

        // 设置logger2日志级别
        // 自定义配置日志级别
        // 创建ConsolHhandler 控制台输出
        ConsoleHandler consoleHandler = new ConsoleHandler();

        // 创建简单格式转换对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();

        // 进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger2.addHandler(consoleHandler);


        // 配置日志具体级别
        logger2.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        logger1.severe("severe");
        logger1.warning("warning");
        logger1.info("info");
        logger1.config("config");
        logger1.fine("fine");
        logger1.finer("finer");
        logger1.finest("finest");
    }
```

### 3.3.5 日志的配置文件

​		默认配置文件路径$JAVAHOME\jre\lib\logging.properties

**自定义日志配置文件**

```
# RootLogger 顶级父元素指定的默认处理器为：ConsoleHandler
handlers= java.util.logging.FileHandler

# RootLogger 顶级父元素默认的日志级别为：ALL
.level= ALL

# 自定义 Logger 使用
com.yj.nz.handlers = java.util.logging.ConsoleHandler
com.yj.nz.level = CONFIG

# 关闭默认配置
com.yj.nz.useParentHanlders = false


# 向日志文件输出的 handler 对象
# 指定日志文件路径 /logs/java0.log
java.util.logging.FileHandler.pattern = D:/logs/java%u.log
# 指定日志文件内容大小
java.util.logging.FileHandler.limit = 50000
# 指定日志文件数量
java.util.logging.FileHandler.count = 1
# 指定 handler 对象日志消息格式对象
java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter
# 指定以追加方式添加日志内容
java.util.logging.FileHandler.append = true


# 向控制台输出的 handler 对象
# 指定 handler 对象的日志级别
java.util.logging.ConsoleHandler.level = ALL
# 指定 handler 对象的日志消息格式对象
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
# 指定 handler 对象的字符集
java.util.logging.ConsoleHandler.encoding = UTF-8

# 指定日志消息格式
java.util.logging.SimpleFormatter.format = %4$s: %5$s [%1$tc]%n
```

**测试加载自定义配置文件**

```
 // 加载自定义配置文件
    @Test
    public void testLogProperties()throws Exception{

        // 读取配置文件，通过类加载器
        InputStream ins = JULTest.class.getClassLoader().getResourceAsStream("logging.properties");
        // 创建LogManager
        LogManager logManager = LogManager.getLogManager();
        // 通过LogManager加载配置文件
        logManager.readConfiguration(ins);

        // 创建日志记录器
        Logger logger = Logger.getLogger("com.yj.nz");

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");


        Logger logger2 = Logger.getLogger("test");

        logger2.severe("severe test");
        logger2.warning("warning test");
        logger2.info("info test");
        logger2.config("config test");
        logger2.fine("fine test");
        logger2.finer("finer test");
        logger2.finest("finest test");

    }
```

### 3.3.6 日志原理解析

**1. 初始化LogManager**

​	1. LogManager加载logging.properties配置

​	2. 添加Logger到LogManager

**2. 从单例LogManager获取Logger**

**3. 设置级别Level，并指定日志记录LogRecord**

**4. Filter提供了日志级别之外更细粒度的控制**

**5. Handler是用来处理日志输出位置**

**6. Formatter是用来格式化LogRecord的**

![image-20221122124606428](assets\image-20221122124606428.png)

## 3.2 LOG4J

### 3.2.1 什么是LOG4J

​	官方网站： http://logging.apache.org/log4j/1.2/

​	**Log4j**是Apache下的一款开源的日志框架，通过在项目中使用 Log4J，我们可以控制日志信息输出到控制台、文件、甚至是数据库中

​	我们可以控制每一条日志的输出格式，通过定义日志的输出级别，可以更灵活的控制日志的输出过程，方便项目的调试！

### 3.2.2 Log4j入门

#### 1. 建立maven工程

#### 2. 添加依赖

```
  		<dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
```

#### 3. java代码

```
public class Log4jTest {

    // 快速入门
    @Test
    public void testQuick()throws Exception{

        // 开启 log4j 内置日志记录
        LogLog.setInternalDebugging(true);

        // 初始化配置信息，在入门案例中暂不使用配置文件
        // BasicConfigurator.configure();

        // 获取日志记录器对象
        Logger logger = Logger.getLogger(Log4jTest.class);
        // 日志记录输出
        logger.info("hello log4j");


        //for (int i = 0; i < 10000; i++) {

            // 日志级别
            logger.fatal("fatal"); // 严重错误，一般会造成系统崩溃并终止运行

            logger.error("error"); // 错误信息，不会影响系统运行
            logger.warn("warn");   // 警告信息，可能会发生问题
            logger.info("info");   // 运行信息，数据连接、网络连接、IO 操作等等
            logger.debug("debug"); // 调试信息，一般在开发中使用，记录程序变量参数传递信息等等

            logger.trace("trace"); // 追踪信息，记录程序所有的流程信息
        //}


        // 再创建一个日志记录器对象
        Logger logger1 = Logger.getLogger(Logger.class);
        logger1.fatal("fatal logger1"); // 严重错误，一般会造成系统崩溃并终止运行
        logger1.error("error logger1"); // 错误信息，不会影响系统运行
        logger1.warn("warn logger1");   // 警告信息，可能会发生问题
        logger1.info("info logger1");   // 运行信息，数据连接、网络连接、IO 操作等等
        logger1.debug("debug logger1"); // 调试信息，一般在开发中使用，记录程序变量参数传递信息等等
        logger1.trace("trace logger1"); // 追踪信息，记录程序所有的流程信息

    }
```

### 3.2.3 日志的级别

#### 1. Log4j日志级别

​	每个 Logger 都被了一个日志级别（log level），用来控制日志信息的输出!

​	日志级别从高到低分为：

​	**1. fatal** 指出每个严重的错误事件将会导致应用程序的退出

​	**2. error** 指出虽然发生错误事件，但仍然不影响系统的继续运行

​	**3. warn**  表明会出现潜在的错误情形

​	**4. info** 一般和在粗粒度级别上，强调应用程序的运行全程

​	**5. debug** 一般用于细粒度级别上，对调试应用程序非常有帮助!

​	**6.  trace** 程序追踪，可用于输出程序运行中的变量，显示执行的流程

​	总结：JUL的日志级别本人介绍的更简洁化，Log4J这一块的日志级别，本人介绍的更详细化！

#### 2. 两个特殊的日志级别

​	**1. OFF** 可用来关闭日志记录

​	**2. ALL** 启用所有消息的日志记录

​	总结：Log4J的两个特殊日志级别与JUL的两个特殊日志级类似！

#### 3. Log4j常用的日志级别

​	Log4j一般只使用4个级别，优先级从高到低为 ERROR > WARN > INFO > DEBUG

### 3.2.4 Log4j组件

#### 1. 何为组件

​	**组件（Component）**就是对数据和方法的简单封装

#### 2. Log4j的主要组成部分

​		Log4J 主要由 **Loggers (日志记录器)**、**Appenders（输出端）**和 **Layout（日志格式化器）**三部分组成

​	**1. Loggers的作用**	

​		Loggers 控制日志的输出级别与日志是否输出；

​	**2. Appender的作用**

​		Appenders 指定日志的输出方式（输出到控制台、文件等）

​	**3. Layout的作用**

​		Layout 控制日志信息的输出格式

##### 2.1 三大组成之Loggers

​		Loggers称为**日志记录器**，负责收集处理日志记录，实例的命名就是**类的全限定名**

​		Logger的名字**大小写敏感**

​		**命名有继承机制：**

​			例如：name为org.apache.commons的logger会继承name为org.apache的logger

​		**特殊的logger：**		

​			Log4J中有一个特殊的logger叫做“root”，这个root是所有logger的根

​			也就意味着其他所有的logger都会直接或者间接地继承自root

​		**root logger如何获取？**

​			root logger可用**Logger.getRootLogger()**方法获取

​			自**log4j 1.2**版以来，Logger类已经取代Category类

​			对于熟悉早期版本的log4j的人来说，Logger类可以被视为Category类的别名

##### 2.2 三大组成之Appenders

​	**Appender** 用来指定日志输出到哪个地方，可以同时指定日志的输出目的地!

​	Log4j 常用的**输出目的地**有以下几种：

| 输出端类型               | 作用                                                         |
| ------------------------ | ------------------------------------------------------------ |
| ConsoleAppender          | 将日志输出到控制台                                           |
| FileAppender             | 将日志输出到文件中                                           |
| DailyRollingFileAppender | 将日志输出到一个日志文件，并且每天输出到一个新的文件         |
| RollingFileAppender      | 将日志信息输出到一个日志文件，并且指定文件的尺寸，<br />当文件大小达到指定尺寸时，会自动把文件改名，同时产生一个新的文件 |
| JDBCAppender             | 把日志信息保存到数据库中                                     |

##### 2.3 三大组成之Layouts

​		布局器 Layouts用于控制日志输出内容的格式，从而达到可以使用各种需要的格式输出日志！

​		Log4j常用的**Layouts**有以下几种：

| 格式化器类型  | 作用                                                         |
| ------------- | ------------------------------------------------------------ |
| HTMLLayout    | 格式化日志输出为HTML表格形式                                 |
| SimpleLayout  | 简单的日志输出格式化，打印的日志格式为（info - message）     |
| PatternLayout | 最强大的格式化期，可以根据自定义格式输出日志，<br />如果没有指定转换格式，就是用默认的转换格式 |

#### 3. Layout的格式

​	在 **log4j.properties**配置文件中，可定义日志输出级别与输出端，在输出端中分别配置日志的输出格式

​		**%m**  输出代码中指定的日志信息
​		**%p**   输出优先级，及 DEBUG、INFO 等

​		**%n**   换行符（Windows平台的换行符为 "\n"，Unix 平台为 "\n"）

​		**%r**   输出自应用启动到输出该 log 信息耗费的毫秒数

​		**%c**   输出打印语句所属的类的全名

​		**%t **  输出产生该日志的线程全名

​		**%d**   输出服务器当前时间，默认为 **ISO8601**，也可以指定格式，如：%d{yyyy年MM月dd日 HH:mm:ss}

​		 **%l**   输出日志时间发生的位置，包括类名、线程、及在代码中的行数

​				如：Test.main(Test.java:10) 

​		**%F**   输出日志消息产生时所在的文件名称 

​		**%L**   输出代码中的行号  

​		**%%**   输出一个 "%" 字符

​	可以**在 % 与字符之间加上修饰符**来控制最小宽度、最大宽度和文本的对其方式！

​	**如：**

​	 **%5c**    输出category名称，最小宽度是5，category<5，默认的情况下右对齐 

​	 **%-5c**    输出category名称，最小宽度是5，category<5，"-"号指定左对齐,会有空格 

​	**%.5c**    输出category名称，最大宽度是5，category>5，就会将左边多出的字符截掉，<5不会有空格 

​	**%20.30c** category名称<20补空格，并且右对齐，>30字符，就从左边交远销出的字符截掉

#### 4. Appender的输出

​	控制台，文件，数据库

```
#指定日志的输出级别与输出端
log4j.rootLogger=INFO,Console

#控制台输出配置
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n

#文件输出配置
log4j.appender.A = org.apache.log4j.DailyRollingFileAppender

#指定日志的输出路径
log4j.appender.A.File = D:/log.txt
log4j.appender.A.Append = true

#使用自定义日志格式化器
log4j.appender.A.layout = org.apache.log4j.PatternLayout

#指定日志的输出格式
log4j.appender.A.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss} [%t:%r] - [%p] %m%n

#指定日志的文件编码
log4j.appender.A.encoding=UTF-8

#mysql
log4j.appender.logDB=org.apache.log4j.jdbc.JDBCAppender
log4j.appender.logDB.layout=org.apache.log4j.PatternLayout
log4j.appender.logDB.Driver=com.mysql.jdbc.Driver
log4j.appender.logDB.URL=jdbc:mysql://localhost:3306/test
log4j.appender.logDB.User=root
log4j.appender.logDB.Password=root
log4j.appender.logDB.Sql=INSERT INTO 
log(
	project_name,
	create_date,
	level,
	category,
	file_name,
	thread_name,
	line,
	all_category,
	message
	) values('yjxz','%d{yyyy-MM-dd HH:mm:ss}','%p','%c','%F','%t','%L','%l','%m')
```

```
CREATE TABLE `log` ( 
	`log_id` int(11) NOT NULL AUTO_INCREMENT, 
	`project_name` varchar(255) DEFAULT NULL COMMENT '目项名', 
	`create_date` varchar(255) DEFAULT NULL COMMENT '创建时间', 
	`level` varchar(255) DEFAULT NULL COMMENT '优先级', 
	`category` varchar(255) DEFAULT NULL COMMENT '所在类的全名', 
	`file_name` varchar(255) DEFAULT NULL COMMENT '输出日志消息产生时所在的文件名称 ', 
	`thread_name` varchar(255) DEFAULT NULL COMMENT '日志事件的线程名', 
	`line` varchar(255) DEFAULT NULL COMMENT '号行', 
	`all_category` varchar(255) DEFAULT NULL COMMENT '日志事件的发生位置', 
	`message` varchar(4000) DEFAULT NULL COMMENT '输出代码中指定的消息', 
	PRIMARY KEY (`log_id`)
);
```

#### 5. 自定义logger

```
# 指定 RootLogger 顶级父元素默认配置信息
# 指定日志级别=trace，使用的 apeender 为=console
log4j.rootLogger = trace,console

# 自定义 logger 对象设置
log4j.logger.com.yj.nz = info,console
log4j.logger.org.apache = error
```

# 4. 日志门面

## 4.1 JCL

### 4.1.1 什么是JCL

​	JCL 全称为**Jakarta Commons Logging**，是Apache提供的一个通用日志API

​	JCL 是为 "所有的Java日志实现"提供一个**统一的接口**，自身也提供一个**日志的实现（SimpleLog）**，

​	但是功能非常弱，所以一般不会单独使用

​	JCL 允许开发人员使用不同的具体**日志实现工具:** 

​		例如：Log4j、JDK 自带的日志（JUL)

​	**JCL 有两个基本的抽象类：**

​		**Log(基本记录器)**和**LogFactory(负责创建Log实例)**

![image-20221122153224953](assets\image-20221122153224953.png)

### 4.1.2 JCL入门

#### 1. 建立maven工程

#### 2. 添加依赖

```
<dependency>    
	<groupId>commons-logging</groupId>    
	<artifactId>commons-logging</artifactId>    
	<version>1.2</version>
</dependency>
```

#### 3. 入门代码

```
    @Test
    public void testQuick() throws Exception {
        // 获取 log日志记录器对象
        Log log = LogFactory.getLog(JCLTest.class);
        // 日志记录输出
        log.info("hello jcl");
    }
```

### 4.1.3 为什么要使用日志门面

> ​	**1. 面向接口开发，不再依赖具体的实现类，减少代码的耦合**
>
> ​	**2. 项目通过导入不同的日志实现类，可以灵活的切换日志框架**
>
> ​	**3. 统一API，方便开发者学习和使用**
>
> ​	**4. 统一配置便于项目日志的管理**

### 4.1.4 JCL原理

**1. 通过LogFactory动态加载Log实现类** 

![image-20221122153745461](assets\image-20221122153745461.png)

**2. 日志门面支持的日志实现数组**

```
private static final String[] classesToDiscover = 
	new String[]{"org.apache.commons.logging.impl.Log4JLogger",                  			
				"org.apache.commons.logging.impl.Jdk14Logger",                  				
				"org.apache.commons.logging.impl.Jdk13LumberjackLogger",                  
				"org.apache.commons.logging.impl.SimpleLog"};
```

**3. 获取具体的日志实现**

```
for(int i = 0; i < classesToDiscover.length && result == null; ++i) {    
	result = this.createLogFromClass(classesToDiscover[i], logCategory, 
true);
}
```

