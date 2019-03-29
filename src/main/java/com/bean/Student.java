package com.bean;

import java.io.Serializable;

/**
 * @author 阿劼
 */
public class Student implements Serializable {
    /**
	* 
	*/
    private Integer id;

    /**
	* 
	*/
    private String name;

    /**
	* 
	*/
    private String password;

    /**
	* 
	*/
    private Integer age;

    /**
	* 
	*/
    private String sex;

    /**
	* 
	*/
//    private Integer classid;
    private Clazz clazz;

    /**
     *
     */
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", clazz=" + clazz +
                ", img='" + img + '\'' +
                '}';
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}