package com.qiujintao.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.qiujintao.util.validate.ValidPassword;
public class User {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.id
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.lastName
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	@Size(max=20)
	private String lastName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.firstName
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	@Size(max=20)
	private String firstName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.nickName
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	@NotBlank
	@Size(min=1,max=45)
	private String nickName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.passwordHash
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	@ValidPassword
	private String passwordHash;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.active
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	private Boolean active;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.role
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	private String role;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.email
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	@Email
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.image
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	private byte[] image;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.id
	 * @return  the value of user.id
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.id
	 * @param id  the value for user.id
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.lastName
	 * @return  the value of user.lastName
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.lastName
	 * @param lastName  the value for user.lastName
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.firstName
	 * @return  the value of user.firstName
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.firstName
	 * @param firstName  the value for user.firstName
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.nickName
	 * @return  the value of user.nickName
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.nickName
	 * @param nickName  the value for user.nickName
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.passwordHash
	 * @return  the value of user.passwordHash
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.passwordHash
	 * @param passwordHash  the value for user.passwordHash
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.active
	 * @return  the value of user.active
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.active
	 * @param active  the value for user.active
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.role
	 * @return  the value of user.role
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public String getRole() {
		return role;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.role
	 * @param role  the value for user.role
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.email
	 * @return  the value of user.email
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.email
	 * @param email  the value for user.email
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.image
	 * @return  the value of user.image
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.image
	 * @param image  the value for user.image
	 * @mbg.generated  Wed Jul 25 18:09:29 CST 2018
	 */
	public void setImage(byte[] image) {
<<<<<<< HEAD

		this.image = image;

=======
		this.image = image;
>>>>>>> refs/remotes/origin/master
	}
}