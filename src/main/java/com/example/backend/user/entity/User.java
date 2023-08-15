package com.example.backend.user.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.backend.track.entity.TrackCount;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, unique = true)
	private String nickname;
	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private UserRoleEnum role;
	@Column
	private Long kakaoId;
	@Column
	private Long googleId;

	@OneToMany(mappedBy = "user")
	private List<TrackCount> trackCounts = new ArrayList<>();

	@OneToMany(mappedBy = "toUser") //user
	private List<Follow> followingList = new ArrayList<>();
	@OneToMany(mappedBy = "fromUser")  //pin
	private List<Follow> followerList = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_key")
	private Image image;

	public void updateUserProfile(String nickname, Image image) {
		this.nickname = nickname;
		this.image = image;
	}

	public User(String email, String password, String nickname, UserRoleEnum role) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.role = role;
	}

	public User(String email, String password, String nickname, UserRoleEnum role, Long googleId, Long kakaoId) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.role = role;
		this.googleId = googleId;
		this.kakaoId = kakaoId;
	}

	public User kakaoIdUpdate(Long kakaoId) {
		this.kakaoId = kakaoId;
		return this;
	}

	public User googleIdUpdate(Long googleId) {
		this.googleId = googleId;
		return this;
	}

	public void updatePassword(String newPassword) {
		this.password = newPassword;
	}
}
