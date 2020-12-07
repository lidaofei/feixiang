package com.feixiang.datastructures.linkedlist;

/**
 * 单向循环链表demo
 */
public class CircleSingleLinkedListDemo {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new  CircleSingleLinkedList();
        HeroNode heroNode = new HeroNode(1,"马云","风清扬");
        circleSingleLinkedList.add(heroNode);

        heroNode = new HeroNode(2,"张勇","逍遥子");
        circleSingleLinkedList.add(heroNode);

        heroNode = new HeroNode(3,"张建锋","行癫");
        circleSingleLinkedList.add(heroNode);

        circleSingleLinkedList.printList();
        System.out.println("=======================");

        heroNode = new HeroNode(2,"张勇","逍遥子");
        circleSingleLinkedList.delete(heroNode);
        circleSingleLinkedList.printList();
        System.out.println("=======================");

        CircleSingleLinkedList josephuCircleSingleLinkedList = new  CircleSingleLinkedList();
        //除了头节点，一共n个元素
        int n = 6;
        //数到第几出队
        int k = 2;
        //约瑟夫问题
        for(int i=1; i<n; i++){
            heroNode = new HeroNode(i,"约瑟夫"+i,"约瑟夫昵称"+i);
            josephuCircleSingleLinkedList.add(heroNode);
        }
        josephuCircleSingleLinkedList.printList();
        System.out.println("=======================");
        josephuCircleSingleLinkedList.josephuPop(k);
    }
}

/**
 * 单向循环链表
 */
class CircleSingleLinkedList {
    private HeroNode headNode;

    public CircleSingleLinkedList(){
        headNode = new HeroNode(0,"","");
    }

    public void add(HeroNode node){
        HeroNode temp = headNode;
        while (temp.next != null){
            if(temp.next == headNode){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.next = headNode;
    }

    public void delete(HeroNode node){
        HeroNode temp = headNode;
        while (temp.next != null){
            if(temp.next.no == node.no){
                break;
            }
            if(temp.next == headNode){
                throw new RuntimeException("没有找到当前需要删除的节点");
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    public void printList(){
        HeroNode temp = headNode;
        if(temp.next == null){
            System.out.println("没有数据");
        }
        while (temp.next != null){
            System.out.println(temp.toString());
            if(temp.next == headNode){
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 约瑟夫问题
     * @param k
     */
    public void josephuPop(int k){
        HeroNode temp = headNode.next;
        HeroNode temp2 = headNode;
        int num = k;
        while (true){
            int count = 1;
            if(temp == temp2){
                System.out.println(temp);
                break;
            }
            while (true){
                if(count == num){
                    break;
                }
                if(temp.next != headNode){
                    count ++;
                }
                temp = temp.next;
                temp2 = temp2.next;
            }
            System.out.println(temp);
            temp2.next = temp.next;
            temp = temp.next;
        }

    }
}
