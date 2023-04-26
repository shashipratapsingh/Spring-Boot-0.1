package com.Zee5blog.Zee5.payloads;

import com.Zee5blog.Zee5.models.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private int id;

	private String content;

}
