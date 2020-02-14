package com.pet.enums;

import java.util.*;

/**
 * 宠物的枚举类型
 * */
public enum PetEnums {

    DOG("dog", 0), CAT("cat",0), CHICKEN("chicken",0), PARROT("parrot",0);

    private String name;

    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private PetEnums(String name, int num){
        this.name = name;
        this.num = num;
    }

    public synchronized static int getNum(String name){
        for(PetEnums pet : PetEnums.values()){
            if(pet.getName().equals(name)){
                return pet.getNum();
            }
        }
        return 0;
    }

    public synchronized static void setNum(String name, int num){
        for(PetEnums pet : PetEnums.values()){
            if(pet.getName().equals(name)){
                pet.setNum(num);
            }
        }
    }

    public static String getList() {
        StringBuffer stringBuffer = new StringBuffer();
        List<PetEnums> petList = sort(PetEnums.values());
        for(PetEnums pet : petList){
            stringBuffer.append(pet.name + ": " + pet.num + "\n");
        }
        return stringBuffer.toString();
    }

    public static List<PetEnums> sort(PetEnums[] petEnums) {
        List<PetEnums> petList = new ArrayList<>(petEnums.length);
        Collections.addAll(petList, petEnums);
        petList.sort((Comparator<PetEnums>) (petEnums1, petEnums2) -> {
            int val1 = PetEnums.getNum(petEnums1.getName());
            System.out.println(val1);
            int val2 = PetEnums.getNum(petEnums2.getName());
            System.out.println(val2);
            return val2 - val1;
        });
        return petList;
    }

    public static void main(String[] args) {
        System.out.println(sort(PetEnums.values()));
    }

}
