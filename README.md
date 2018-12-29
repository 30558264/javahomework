

# 设计说明

## 运行结果

![](/picture/1.png)

![](/picture/2.png)

## 功能说明

1、根据作业要求，葫芦娃们和妖精们在20*16的地图上摆开不同的阵型（地图右侧有选择阵型的按钮，可以选择你想要的阵型进行对战）。

2、按空格键开始游戏，生物们随机的进行移动，若两个不同阵营的生物相遇，就会互相攻击一下，若某个生物收到攻击死亡，则在原地留下尸体（葫芦娃们会变成葫芦，妖精们会变成一堆骷髅）；若没有死亡就会后退一步（若本就在地图边界就不会后退）。

3、游戏就结束后，按R键（英文输入法）重置游戏。

4、游戏结束后，按S键（英文输入法）保存当局游戏到指定文件。

5、若想要复盘，要在游戏未开始或已经结束的情况下，按L键（英文输入法）打开想要复盘的游戏数据。

## UML图

生物

![](/picture/UML1.png)

阵型

![](/picture/UML2.png)

## 面向对象实现

### 封装、继承、多态

由上述UML图就可以看出：

1、Formation对外只提供formation函数，不提供具体实现；

2、不同生物体继承自父类Creature；

3、接口Formation有六个不同的类实现，体现了多态。

## 异常处理

### 输入输出异常

```
try
{
	...
}
catch (IOException e)
{
    e.printStackTrace();
}
```

### CyclicBarrier异常

```
try {
    Thread.sleep(100);
} catch (InterruptedException e1) {
    e1.printStackTrace();
}
```

### 线程异常

```
try
{
    cyclicBarrier.await();
}
catch (InterruptedException | BrokenBarrierException e)
{
    e.printStackTrace();
}
```

## 集合泛型

```
protected List<Creature> creatures=new ArrayList<Creature>();
```

## 线程同步机制

使用CyclicBarrier实现多线程的同步：CyclicBarrier中调用battlefield的update函数，计算出各个生物体下一步移动的位置。

```
cyclicBarrier=new CyclicBarrier(calabashes.getCreatures().size() + monsters.getCreatures().size(), new Runnable() {
    @Override
    public void run() {
        Global.getBattlefield().update();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
});
```

## 注解、测试

使用了@Override、@Test注解分别实现对父类函数的重写、单元测试用例。

```
@Override
public void run(){...}

@Override
public void formation(Creatures creatures){...}

@Test
public void initTest(){...}

@Test
public void clearTest(){...}
```

## 致谢

感谢曹老师、余老师还有助教们的辛勤付出！！！