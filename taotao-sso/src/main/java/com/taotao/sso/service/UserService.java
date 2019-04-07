package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
	TaotaoResult checkData(String content, Integer type);

	TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

	TaotaoResult createUser(TbUser user);

	TaotaoResult getUserByToken(String token);
	TaotaoResult userLogout(String token);
}
