package com.feixiang.datastructures.linkedlist;

/**
 * 单链表demo
 * @author lidaofei
 * @date 2020/4/19 23:31
 */
public class SingleLinkedListDemo {
    //1.添加节点
    //2.删除节点
    //3.修改节点
    public static void main(String[] args) {
        //1.添加节点
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode heroNode = new HeroNode(1,"马云","风清扬");
        singleLinkedList.add(heroNode);

        heroNode = new HeroNode(2,"张勇","逍遥子");
        singleLinkedList.add(heroNode);

        heroNode = new HeroNode(3,"张建锋","行癫");
        singleLinkedList.add(heroNode);

        singleLinkedList.printNode();
        System.out.println("=================");

        //2.删除节点
        int no = 2 ;
        singleLinkedList.delete(no);
        singleLinkedList.printNode();
        System.out.println("=================");

        //3.修改节点
        heroNode = new HeroNode(3,"张建锋2","行癫2");
        singleLinkedList.update(heroNode);
        singleLinkedList.printNode();
        System.out.println("=================");

        //4.删除搜索节点
        singleLinkedList.deleteAll();
        singleLinkedList.printNode();
        System.out.println("=================");

        //4.按no顺序添加
        heroNode = new HeroNode(3,"马云","风清扬");
        singleLinkedList.addOrder(heroNode);

        heroNode = new HeroNode(1,"张勇","逍遥子");
        singleLinkedList.addOrder(heroNode);

        heroNode = new HeroNode(2,"张建锋","行癫");
        singleLinkedList.addOrder(heroNode);

        singleLinkedList.printNode();
        System.out.println("=================");

        //5.获取链表的长度
        int length = getLength(singleLinkedList.getHeadNode());
        System.out.printf("链表的长度：%d\n",length);
        System.out.println("=================");

        //6.查看有效节点长度（和上面一样）
        int size = singleLinkedList.getLength();
        System.out.println("有效节点长度 size="+size);
        System.out.println("=================");

        //7.查找单链表中倒数第k个节点

    }

    //1.获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
    //定义一个temp节点，遍历累加
    public static int getLength(HeroNode headNode){
        HeroNode temp = headNode;
        int count = 0;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
            count ++;
        }
        return count;
    }


}





/**
 * 单链表
 */
class SingleLinkedList {
    //1.定义一个头节点，头节点不要动，不存放具体数据
    //2.添加节点，使用一个temp节点，循环找到最后一个节点，把next指向新节点
    //3.删除节点，使用一个temp节点，循环找到需要删除节点的前一个节点，
    //比较时，temp.next.no == no 时，就把temp.next = temp.next.next。
    //4.更新节点，使用temp节点，循环找到当前节点
    //比较 temp.no == no时，temp.name=name,temp.nickName=nickName。
    //5.打印所有节点，使用temp节点，循环打印

    //1.定义一个头节点，头节点不要动，不存放具体数据
    public HeroNode headNode;
    public SingleLinkedList(){
        headNode = new HeroNode(0,null,null);
    }

    public HeroNode getHeadNode(){
       return this.headNode;
    }

    //2.添加节点，使用一个temp节点，循环找到最后一个节点，把next指向新节点
    public void add(HeroNode heroNode){
        HeroNode temp = headNode;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //2.2 按照no按顺序添加，使用temp节点，
    // 找到temp.next.no > heroNode.no时，
    // heroNode.next = temp.next; temp.next = heroNode;
    public void addOrder(HeroNode heroNode){
        HeroNode temp = headNode;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                flag= true;
                break;
            }
            if(temp.next.no > heroNode.no){
                flag= true;
                break;
            }
            temp = temp.next;
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    //3.删除节点，使用一个temp节点，循环找到需要删除节点的前一个节点，
    public void delete(int no){
        HeroNode temp = headNode;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(!flag){
            throw new RuntimeException("没有找到需要删除的节点");
        }
        temp.next = temp.next.next;
    }

    //3.1 删除所有节点
    public void deleteAll(){
        headNode.next = null;
    }

    //4.更新节点，使用temp节点，循环找到当前节点
    public void update(HeroNode heroNode){
        HeroNode temp = headNode;
        boolean flag = false;
        while (true){
            if(temp.no == heroNode.no){
                flag = true;
                break;
            }
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        if(!flag){
            throw new RuntimeException("没有找到需要更新的节点");
        }
        temp.name = heroNode.name;
        temp.nickName = heroNode.nickName;
    }

    //5.打印所有节点，使用temp节点，循环打印
    public void printNode(){
        HeroNode temp = headNode;
        while (true){
            System.out.println(temp.toString());
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
    }

    //6.求单链表中有效节点的个数
    public int getLength(){
        HeroNode temp = headNode.next;
        if(temp == null){
            return 0;
        }
        int length = 0;
        while(true){
            length ++;
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        return length;
    }

    //6.查找单链表中倒数第k个节点
    //思路：1.获取整个链表的长度
    //2.获取倒数第二个元素，就是总长度5-2=3，就是正数第3个
    public HeroNode getLastNode(int lastNum){
        int length = getLength();
        //整数第几个元素，5-2=3，第三个元素
        int index = length - lastNum;
        HeroNode temp = headNode;
        int tempCount = 0;
        while (true){

            if(temp.next == null){
                throw new RuntimeException("没有元素");
            }
            if(index < 0){
                throw new RuntimeException("没有那么多元素");
            }
            if(tempCount == index){
                break;
            }
            tempCount ++;
            temp = temp.next;
        }
        return temp;
    }


}

/**
 * 英雄节点
 */
class HeroNode {
    //编号
    public int no;

    //名字
    public String name;

    //昵称
    public String nickName;

    //下一个节点
    public HeroNode next;

    public HeroNode(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName=nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
