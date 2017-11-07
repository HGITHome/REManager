package com.dgut.modules.customer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dgut.modules.common.enumeration.SexEnum;

@Entity
@Table(name="customer")
public class Customer implements Serializable {

	/**
	 * �ͻ���
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String phone;
	private SexEnum sex;
	private String email;

    /**
     * @return ���ؿͻ���Ӧ��ΨһID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    /**
     * @return ���ؿͻ�������
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * @return ���ؿͻ���ϵ�绰 ֵ��: �ֳ� < 13, (+86-)?1[3\d|4(5|7)|5\d|7(6|7|8)|8\d]\d{9}
     */
    @Column(name = "custom_phone", length = 16)
    public String getPhone() {
        return phone;
    }

    /**
     * @return ���ؿͻ����Ա� ֵ��: ö���� (SexEnum.MALE/SexEnum.FEMALE)
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "sex")
    public SexEnum getSex() {
        return sex;
    }

    /**
     * @param id �ͻ���ΨһID ������: ������������ݿ���
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param name �ͻ������� ������: �����Ļ�Ӣ��
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param phone �ͻ���ϵ�绰 ������: �ֳ�<13, ��ʽ: (+86-)?1[3\d|4(5|7)|5\d|7(6|7|8)|8\d]\d{9}
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @param sex �ͻ��Ա� ������: SexEnum.MALE/SexEnum.FEMALE
     */
    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    @Column(name="email",nullable=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    
}
