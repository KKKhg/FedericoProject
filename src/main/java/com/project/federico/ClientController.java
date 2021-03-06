package com.project.federico;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import lombok.extern.log4j.Log4j;
import paging.PageMaker;
import paging.SearchCriteria;
import service.ClientServiceImpl;
import service.FranchiseService;
import service.HeadOfficeService;
import service.MenuServiceImpl;
import service.OrderService;
import service.SendService;
import vo.CartVO;
import vo.ClientVO;
import vo.ComplainBoardVO;
import vo.EmailVO;
import vo.EventBoardVO;
import vo.FranchiseVO;
import vo.HeadOfficeVO;
import vo.MenuVO;
import vo.NoticeBoardVO;
import vo.OrderDetailListVO;
import vo.OrderListVO;

@RequestMapping(value = "/client")
@Log4j
@Controller
public class ClientController {

	@Autowired
	SendService sendService;
	@Autowired
	MenuServiceImpl menuService;
	@Autowired
	ClientServiceImpl clientService;
	@Autowired
	OrderService orderService;
	@Autowired
	FranchiseService fcService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	HeadOfficeService headOfficeService;

	
	// ??????????????? ?????? + ???????????? ?????????
	@RequestMapping(value = "/ordercomplete")
	public ModelAndView ordercomplete(ModelAndView mv, HttpServletRequest request, HttpSession session, ClientVO clientVo, RedirectAttributes rttr) {
		List<CartVO> list = (List<CartVO>)session.getAttribute("list");
		Map<String, Object> params = new HashMap<String, Object>();
		String fcId;
		String memo;
		
		// iampay ??? ??????
		if("iam".equals(request.getParameter("iam"))){
			fcId = request.getParameter("fcId");
			memo = request.getParameter("memo");
		}else {
			fcId = (String)session.getAttribute("fcId");
			memo = (String)session.getAttribute("memo");
		}
		
		// map??? ???????????? ??? ??????
		if(session.getAttribute("clientLoginID") != null) {
			clientVo.setClientId((String)session.getAttribute("clientLoginID"));
			clientVo = clientService.selectOne(clientVo);
			params.put("clientId", clientVo.getClientId());
			params.put("fcId", fcId);
			params.put("memo", memo);
			params.put("clientName", clientVo.getClientName());
			params.put("clientAddress", clientVo.getClientAddress());
			params.put("clientPhone", clientVo.getClientPhone());
			params.put("memberYN", "Y");
			params.put("orderNumber", null);
		} else {
			params.put("clientId", "NONE");
			params.put("fcId", fcId);
			params.put("memo", memo);
			params.put("clientName", (String)session.getAttribute("nonName"));
			params.put("clientAddress", (String)session.getAttribute("nonAddress"));
			params.put("clientPhone", (String)session.getAttribute("nonPhone"));
			params.put("memberYN", "N");
			params.put("orderNumber", null);
		}
		
		// ???????????? ?????????
		if (orderService.insertOrderList(params) > 0) {
			params.put("orderNumber", (int)params.get("orderNumber"));
			params.put("list", list);
			
			//??????????????? ?????????????????? ??????
			String deliveryTime = fcService.selectDeliveryTimebyFcId(fcId);
			rttr.addFlashAttribute("deliveryTime", deliveryTime);
			
			
			// ?????????????????? ?????????
			if(orderService.insertOrderDetailList(params) > 0) {
				
				//???????????? ???????????? ??????
				String phone = "";
				if(session.getAttribute("clientLoginID") != null) {
					phone = clientVo.getClientPhone();
				}else {
					phone = (String)session.getAttribute("nonPhone");
				}
				sendService.orderCompeleteSmsSend(phone, deliveryTime, fcId);
				
				
				// ???????????? ?????????
				if ("Y".equals(params.get("memberYN"))) {
					clientService.deleteCartbyClientId(clientVo.getClientId());
				} 
				if(session.getAttribute("list") != null) {
					session.removeAttribute("list");
				}
				session.removeAttribute("listSize");
				session.removeAttribute("fcId");
			}
			
		}
		mv.setViewName("redirect:ordercompletef");
		return mv;
	}
	
	// ???????????? ??? redirect ???
	@RequestMapping(value = "/ordercompletef")
	public String orderCompletf() {
		return "client/orderComplete";
	}
	
	// ??????????????? ????????????
	  @RequestMapping(value = "/kakaoPay") 
	  @ResponseBody
	  public String kakaoPay(HttpServletRequest request) { 
		  if(request.getSession(false) != null) {
			  request.getSession(false).setAttribute("fcId", request.getParameter("fcId"));
			  request.getSession(false).setAttribute("memo", request.getParameter("memo"));
		  }
		  try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.addRequestProperty("Authorization", "KakaoAK 6de9f6b2f650d7fdeeb0c99a8f9cb163");
			connection.addRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.setDoOutput(true);
			String parameter = "cid=TC0ONETIME"
					+ "&partner_order_id="+request.getParameter("partner_order_id")
					+ "&partner_user_id="+request.getParameter("partner_user_id")
					+ "&item_name="+request.getParameter("item_name")
					+ "&quantity="+request.getParameter("quantity")
					+ "&total_amount="+request.getParameter("total_amount")
					+ "&tax_free_amount=0"
					+ "&approval_url=http://localhost:8080/federico/client/ordercomplete"
					+ "&cancel_url=http://localhost:8080/federico/client/home"
					+ "&fail_url=http://localhost:8080/federico/client/home";
			OutputStream Output = connection.getOutputStream();
			DataOutputStream dataOutput = new DataOutputStream(Output);
			dataOutput.writeBytes(parameter);
			dataOutput.close();
			
			int resultCode = connection.getResponseCode();
			InputStream input;
			if(resultCode == 200) {
				input = connection.getInputStream();
			} else {
				input = connection.getErrorStream();
			}
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");
			BufferedReader bReader = new BufferedReader(reader);
			return bReader.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		  return "{\"result\":\"NO\"}";
	  }
	  
	
	// ??????????????? : ????????? ??????
	@RequestMapping(value = "/selectarea")
	public ModelAndView selectarea(ModelAndView mv, @RequestParam("area") String area) {
		
		List<FranchiseVO> list = fcService.selectListbyArea(area);
		if (list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("success","success");
		} else {
			mv.addObject("success","fail");
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	// ??????????????? ?????? - ??????????????? ???????????????????????? ??????,??????,???????????? ????????????, ??????????????? ?????????????????????
	@RequestMapping(value = "/orderInfo")
	public ModelAndView orderInfo(ModelAndView mv, HttpServletRequest request, HttpSession session, ClientVO vo) {
		String uri = "client/orderInfo";
		
		if (session.getAttribute("clientLoginID") != null) {
			vo.setClientId(session.getAttribute("clientLoginID").toString());
			mv.addObject("clientAddress", clientService.selectOne(vo).getClientAddress());
			mv.addObject("clientPhone", clientService.selectOne(vo).getClientPhone());
			mv.addObject("clientName", clientService.selectOne(vo).getClientName());
		} else if(session.getAttribute("nonAddress") != null) {
			mv.addObject("clientAddress", (String)session.getAttribute("nonAddress"));
			mv.addObject("clientPhone", (String)session.getAttribute("nonPhone"));
			mv.addObject("clientName", (String)session.getAttribute("nonName"));
		} else if(session.getAttribute("nonName") != null) {
			uri = "client/nonOrderAddress";
		} else {
			uri="redirect:clientLoginf";
		}
		
		mv.setViewName(uri);
		return mv;
	}	
	
	
	
	// ????????? ????????????????????? ?????? ??????
	@RequestMapping(value = "/deleteCartNM")
	public ModelAndView deleteCartNoneMember(ModelAndView mv, HttpSession session, @RequestParam("index") int index) {

		List<CartVO> list = (List<CartVO>) session.getAttribute("list");
		list.remove(index);
		session.setAttribute("list", list);
		session.setAttribute("listSize", list.size());

		mv.setViewName("jsonView");
		return mv;
	}

	// ????????? ??????????????????????????? ???????????? ??????
	@RequestMapping(value = "/updateCartNM")
	public ModelAndView updateCartNM(ModelAndView mv, HttpSession session, CartVO vo,
			@RequestParam("index") int index) {
		if (session != null) {
			List<CartVO> list = (List<CartVO>) session.getAttribute("list");
			list.get(index).setMenuQty(vo.getMenuQty());
			session.setAttribute("list", list);
			session.setAttribute("listSize", list.size());
			mv.addObject("success", "success");
		} else {
			mv.addObject("success", "fail");
		}
		mv.setViewName("jsonView");
		return mv;
	}

	// ????????? ???????????? ?????? ??????
	@RequestMapping(value = "/addCartNM")
	public ModelAndView addCartNoneMember(ModelAndView mv, HttpSession session, MenuVO menuVo, CartVO cartVo) {
		List<CartVO> list = null;
		if (session.getAttribute("list") != null) {
			list = (List<CartVO>) session.getAttribute("list");
		} else {
			list = new ArrayList<CartVO>();
		}
		
		menuVo = menuService.selectMenuOne(menuVo);
		if (menuVo != null) {
			cartVo.setMenuVo(menuVo);
			list.add(cartVo);
			session.setAttribute("list", list);
			session.setAttribute("listSize", list.size());
			mv.addObject("success", "success");
		} else {
			mv.addObject("success", "fail");
		}

		mv.setViewName("jsonView");
		return mv;
	}

	// ?????? ?????????????????? ?????? ??????
	@RequestMapping(value = "/deleteCartM")
	public ModelAndView deleteCartM(ModelAndView mv, CartVO vo, HttpSession session) {

		if (clientService.deleteCart(vo) > 0) {
			mv.addObject("success", "success");
			session.setAttribute("listSize", Integer.parseInt(session.getAttribute("listSize").toString())-1);
		}
		else mv.addObject("success", "fail");
		mv.setViewName("jsonView");
		return mv;
	}

	// ?????? ??????????????????????????? ???????????? ??????
	@RequestMapping(value = "/updateCartM")
	public ModelAndView updateCartM(ModelAndView mv, HttpSession session, CartVO vo) {

		if (clientService.updateCart(vo) > 0) {
			List<CartVO> list = clientService.selectCartbyClient(vo);
			session.setAttribute("list", list);
			mv.addObject("success", "success");
		}
		else {
			mv.addObject("success", "fail");
		}
		mv.setViewName("jsonView");
		return mv;
	}

	// ?????? ???????????? ?????? ??????
	@RequestMapping(value = "/addCartM")
	public ModelAndView addCartMember(ModelAndView mv, HttpSession session, CartVO vo, MenuVO menuVo) {
		vo.setMenuVo(menuVo);
		
		if(clientService.insertCart(vo) > 0) {
			mv.addObject("success","success");
			if (session.getAttribute("listSize") == null) {
			session.setAttribute("listSize", 1);
			} else {
				session.setAttribute("listSize", Integer.parseInt(session.getAttribute("listSize").toString())+1);
			}
		}
		else mv.addObject("success","fail"); 
		mv.addObject("success","success"); 
		
		mv.setViewName("jsonView");
		return mv;
	}

	// ???????????????????????? ??????(??????)
	@RequestMapping(value = "/cart")
	public ModelAndView cart(ModelAndView mv, HttpSession session, CartVO vo, MenuVO menuVo,
			@RequestParam("m") String m) {
		// ????????? ?????? db?????? ???????????? session??? ?????????.
		if (! "n".equals(m)) {
			vo.setClientId((String) session.getAttribute("clientLoginID"));
			List<CartVO> list = clientService.selectCartbyClient(vo);
			if (list.size() > 0) {
				session.setAttribute("list", list);
				session.setAttribute("listSize", list.size());

			} else
				session.setAttribute("list", null);
		}
		mv.setViewName("client/cart");
		return mv;
	}

	// flag??? ?????? ??????????????? ??????
	@RequestMapping(value = "/menuList")
	public ModelAndView menuList(ModelAndView mv, MenuVO vo, HttpServletRequest request, HttpSession session) {
		List<MenuVO> list = menuService.selectMenuListbyFlag(vo);
		if (list != null) {
			mv.addObject("list", list);   
			mv.addObject("flag", vo.getMenuFlag());
			session.setAttribute("listsize", list.size());
		}
		
		mv.setViewName("client/menu");
		return mv;
	}

	@RequestMapping(value = { "/", "/home" })
	public ModelAndView clientHome(ModelAndView mv, HttpSession session) {
		if(session.getAttribute("list") != null) {
			List<CartVO> list = (List<CartVO>)session.getAttribute("list");
			if(list.size() > 0) {
				mv.addObject("listSize", list.size());
			}
		}
		
		mv.setViewName("client/pizzaMain");
		return mv;
	}

	@RequestMapping(value = "clientLoginf")
	public String clientLoginf(ModelAndView mv) {
		return "client/clientLoginForm";
	}

// ?????????(?????????)
	@PostMapping(value = "/clientLogin")
	public ModelAndView clientLogin(HttpSession session,HttpServletRequest request, HttpServletResponse response, ModelAndView mv,
			ClientVO vo, CartVO cartVo , @RequestParam("autoLogin") String autoLogin) throws ServletException, IOException {
		// ?????? ??????
		String password = vo.getClientPassword();
		String uri = "/client/clientLoginForm";
		
		System.out.println(vo.getClientPassword());
		System.out.println(vo.getClientId());
		
		vo = clientService.selectOne(vo);
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		
		// ?????? ??????
		if (vo != null) { // ID??? ?????? -> Password ??????
			if (passwordEncoder.matches(password, vo.getClientPassword())) {
				// ????????? ?????? -> ????????? ?????? session??? ??????, home
				
				request.getSession().setAttribute("clientLoginID", vo.getClientId());
				request.getSession().setAttribute("clientLoginName", vo.getClientName());
				uri = "redirect:home";
				log.info(autoLogin);
				//???????????????
				if ("true".equals(autoLogin)){
					log.info("??????????");
	                Cookie cookie =new Cookie("loginCookie", session.getId());
	                cookie.setPath("/");
	                int amount =60 *60 *24 *7;
	                cookie.setMaxAge(amount); //7???
	                // ????????? ????????? ??????.
	                response.addCookie(cookie);
	                
	                Date sessionLimit =new Date(System.currentTimeMillis() + (1000*amount));
					params.put("clientId", vo.getClientId());
					params.put("sessionId", session.getId());
					params.put("next", sessionLimit);
					clientService.keepLogin(params);
	            }
			} else {
				mv.addObject("message", "Password??? ????????????????????????.");
			}
		} else {
			mv.addObject("message", "??????????????? ????????????. ID??? ??????????????????.");
		} // if
		
		// ????????? ??? ??????????????? ?????? ????????? nav??? ????????????
		List<CartVO> list = clientService.selectCartbyClient(cartVo);
		if(list != null) {
			request.getSession(false).setAttribute("listSize", list.size());
		}
		
		mv.setViewName(uri);

		return mv;
	}// login

	// ???????????? (?????????)
	@RequestMapping(value = "/clientLogout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, ModelAndView mv,
			RedirectAttributes rttr, ClientVO vo) throws ServletException, IOException {

		// 1) request ??????
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(false);
		
		// ** session ???????????? ?????? ??? ????????????
		// => ????????????: ?????????, true, false
		// => false : session ??? ????????? null ??? return;
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (session != null) {
			vo.setClientId((String)session.getAttribute("clientLoginID"));
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request,"loginCookie");
            if ( loginCookie !=null ){
                // null??? ????????? ????????????!
                loginCookie.setPath("/");
                // ????????? ?????? ??? ??????????????? 0?????? ???????????? ??? !!! invalidate????????? ??????.
                loginCookie.setMaxAge(0);
                // ?????? ????????? ????????????.
                response.addCookie(loginCookie);
                 
                // ????????? ?????????????????? ??????????????? ?????????????????? ?????? ??????????????????.
                Date date =new Date(System.currentTimeMillis());
                params.put("clientId", vo.getClientId());
				params.put("sessionId", session.getId());
				params.put("next", date);
                clientService.keepLogin(params);
            }
		}
		String uri = "redirect:home";
		rttr.addFlashAttribute("message", "???????????? ??????");

		// mv.addObject("message","~~~") -> forward
		// uri = "home" -> forward

		mv.setViewName(uri);

		return mv;
	}// logout

	// ???????????? ????????????
	@GetMapping("/sendSms")
	public @ResponseBody ModelAndView sendSMS(ModelAndView mv, ClientVO vo) {

		
		String phoneNumber = vo.getClientPhone();
		vo = clientService.selectOnePhone(vo);
		if (vo != null) {
			mv.addObject("selectOne", vo);
		} else {
			Random rand = new Random();
			String numStr = "";
			for (int i = 0; i < 6; i++) {
				String ran = Integer.toString(rand.nextInt(10));
				numStr += ran;
			}
			System.out.println("????????? ?????? : " + phoneNumber);
			System.out.println("???????????? : " + numStr);
			sendService.certifiedPhoneNumber(phoneNumber, numStr);
			mv.addObject("numStr", numStr);
		}
		mv.setViewName("jsonView");
		return mv;
	}
	// ???????????????????????? ????????????
		@GetMapping("/findPwSendSMS")
		public @ResponseBody ModelAndView findPwSendSMS(ModelAndView mv, ClientVO vo) {
			String clientName = vo.getClientName();
			String phoneNumber = vo.getClientPhone();
			vo = clientService.selectOne(vo);
			if(vo!=null) {
				if (vo.getClientName().equals(clientName) == false) {
					log.info(clientName + "," + vo.getClientName());
					mv.addObject("message", "??????????????? ?????? ???????????????. ??????????????? ?????? ??????????????????");
				} else if (vo.getClientPhone().equals(phoneNumber) == false) {
					mv.addObject("message", "??????????????? ?????? ???????????????. ??????????????? ?????? ??????????????????");
					log.info(phoneNumber + "," + vo.getClientPhone());
				} else {
					Random rand = new Random();
					String numStr = "";
					for (int i = 0; i < 6; i++) {
						String ran = Integer.toString(rand.nextInt(10));
						numStr += ran;
					}
					System.out.println("????????? ?????? : " + phoneNumber);
					System.out.println("???????????? : " + numStr);
					sendService.certifiedPhoneNumber(phoneNumber, numStr);
					mv.addObject("numStr", numStr);
					mv.addObject("clientId", vo.getClientId());
				}
			}else {
				mv.addObject("message", "??????????????? ????????????.");
			}
			mv.setViewName("jsonView");
			return mv;
		}
	
	

	
	// ????????? ?????? ?????? ??????
	@RequestMapping(value = "/nonaddress")
	public ModelAndView nonAddress(ModelAndView mv, HttpSession session, @RequestParam("nonAddress") String nonAddress) {
		
		session.setAttribute("nonAddress", nonAddress);
		mv.addObject("success","success");
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	// ??????????????? ??????????????? ??????
	@RequestMapping(value = "nonOrderf")
	public ModelAndView nonOrderf(ModelAndView mv, HttpSession session, HttpServletRequest request) {
		String uri = "client/nonOrderAddress";
		if(session.getAttribute("nonName") == null) {
			session.setAttribute("nonName", request.getParameter("nonName"));
			session.setAttribute("nonPhone", request.getParameter("nonPhone"));
		}
		
		mv.setViewName(uri);

		return mv;
	}
	
	
	
		// ??????????????? ??????
		@RequestMapping(value = "clientJoinf")
		public ModelAndView clientJoinf(ModelAndView mv) {
			String uri = "client/clientJoinForm";
			mv.setViewName(uri);
			return mv;
		}
		// ??????????????? ????????? ??????
		@PostMapping(value = "clientJoin2ndf")
		public ModelAndView clientJoinf2ndf(ModelAndView mv, ClientVO vo) {
			
			
			mv.addObject("clientName", vo.getClientName());
			mv.addObject("clientPhone", vo.getClientPhone());
			mv.addObject("smsCheck", vo.getSmsCheck());
			mv.addObject("emailCheck", vo.getEmailCheck());
			
			String uri = "client/clientJoinFormDetail";
			mv.setViewName(uri);
			return mv;
		}
		
		//?????????????????????
		@RequestMapping(value = "/clientSelectOne")
		public ModelAndView clientSelectOne(ModelAndView mv, ClientVO vo) {
			vo = clientService.selectDubCheck(vo);

			if (vo != null)
				mv.addObject("clientDetail", vo); // MyBatis ?????? null , size()>0 ?????? ??????
			else
				mv.addObject("message", "????????? ????????????.");
			mv.setViewName("jsonView");
			return mv;
		}
		
		//????????????
		@RequestMapping(value = "clientJoin")
		public ModelAndView clientJoin(ModelAndView mv, ClientVO vo , RedirectAttributes rttr, HttpSession session, CartVO cartVo) {
			
			
			vo.setClientPassword(passwordEncoder.encode(vo.getClientPassword()));
			if (clientService.insertClient(vo) > 0) {
				rttr.addAttribute("message", "??????????????? ?????????????????????.");
				// ???????????? ??? ???????????? ????????? ????????????????????? ??????
				if(session.getAttribute("list") != null) {
					List<CartVO> list = (List<CartVO>) session.getAttribute("list");
					if(list.size() > 0) {
						for(CartVO imsi : list) {
							imsi.setClientId(vo.getClientId());
							clientService.insertCart(imsi);
						}
					}
				}
			} else {
				rttr.addAttribute("message", "??????????????? ?????????????????????");
			}
			mv.setViewName("redirect:clientLoginf");
			return mv;
		}// join
		

		//?????????????????????
		@RequestMapping(value = "nonOrderDetail")
		public ModelAndView nonOrderDetail(ModelAndView mv,
				@RequestParam("nonName") String nonName, @RequestParam("nonPhone") String nonPhone, 
				OrderListVO ordervo, OrderDetailListVO orderDvom , ClientVO vo) throws IOException {
			mv.setViewName("client/nonOrderDetail");
			vo.setClientPhone(nonPhone);
			vo = clientService.selectOnePhone(vo);
			
			if (vo != null) {
				mv.addObject("selectOne", "?????????????????????. ????????? ??? ??????????????????.");
			} else {
				ordervo.setClientPhone(nonPhone);
				ordervo = orderService.selectOrderbyPhone(ordervo);
				if (ordervo == null) {
					mv.addObject("message", "??????????????? ????????????.");
				} else {
					List<OrderDetailListVO> list = orderService.selectDetailbyOrderNumber(ordervo.getOrderNumber());
					if (list != null) {
						mv.addObject("orderInfo", ordervo);
						mv.addObject("list", list);
					} else {
						mv.addObject("message", "??????????????? ????????????.");
					}
				}
			}
			return mv;

		}
		
		//????????????
		@RequestMapping(value = "orderCancel")
		public ModelAndView orderCancel(ModelAndView mv, OrderListVO vo) {
			if(orderService.orderCancel(vo)>0) {
				sendService.orderCancelSmsSend(vo.getClientPhone());
				mv.addObject("success", "??????");
			}else {
				mv.addObject("success", null);
			}
			mv.setViewName("jsonView");
			return mv;
		}
		
//===========================<< MAP START >>==========================
		@RequestMapping(value ="/fcSearch")
		public ModelAndView fcsearch (ModelAndView mv, FranchiseVO vo, HttpServletRequest request, HttpSession session) {
			
			if("card".equals(request.getParameter("card"))) {
				List<FranchiseVO> list = (List<FranchiseVO>)session.getAttribute("fcInfo2");
//				log.info(list.toString());
				mv.addObject("list", list);
						
			}
				mv.setViewName("/client/fcSearch");						 
			// if
				
			return mv;
			}

		@RequestMapping(value ="/fcSearchArea")
		public ModelAndView fcsearchmain (ModelAndView mv, FranchiseVO vo, HeadOfficeVO hvo, HttpServletRequest request, HttpSession session) {
			Map<String, Object> params = new HashMap<String, Object>();			
			// ???????????? ????????? ??????????????? ??? ???????????? ?????? list??? ????????????.?
			// ???? 
			// ??? ???????????? ????????? ?????????(key,value)??? ???????????? ????????????.
			params.put("Depth1",request.getParameter("Depth1")); // ajax?????? ?????? ????????? ?????? ?????????.
			params.put("Depth2",request.getParameter("Depth2")); // request??? ????????? ?????? ????????????. ???????????? ??????	
			
			List<FranchiseVO> list = fcService.selectFcAddress(params);
			
			if(list != null) {
						mv.addObject("list", list);
						session.setAttribute("fcInfo2", list);
						mv.addObject("success","success");	
//						log.info("fcSearchArea =>"+list.toString());
			}else {
				mv.addObject("success","fail");					
			}			
				mv.setViewName("jsonView");						 
		
			return mv;
			}
		
		@RequestMapping(value = "/fcSearchLocation")
		public ModelAndView fcSearchLocation (ModelAndView mv, FranchiseVO vo, HeadOfficeVO hvo, HttpServletRequest request, HttpSession session) {
			List<FranchiseVO> list = fcService.selectFcLocation(request.getParameter("fcAddress_keyword"));
			
			log.info("fcSearchLocation list =>"+list);
			
			if(list!= null && list.size() >0) {				
				mv.addObject("list",list);
				mv.addObject("success","success");
			}else {
				mv.addObject("success","fail");
			}			
				mv.setViewName("jsonView");						 
			return mv;
			}// fcSearchLocation
	
		
		@RequestMapping(value = "/fcAllAddress")
		public ModelAndView fcAllAddress (ModelAndView mv, FranchiseVO vo) {
			
			List<FranchiseVO> list = fcService.selectFcAllAddress(vo);
			log.info("fcAllAddress list =>"+list.toString());
			if(list != null) {
				mv.addObject("list",list);
				mv.addObject("success","success");
			}
			mv.setViewName("jsonView");
			return mv;
		}//fcAllAddress
		
		
		
		
		
		
		
		
		
		
		
		//===============================<<MAP END>>============================		
		
		
		
		
		
		
		
		
		
		
		
		//??????????????????
		@RequestMapping(value = "cscenterf")
		public ModelAndView cscenterf(ModelAndView mv, SearchCriteria cri, PageMaker pageMaker) {
			cri.setSnoEno();
			List<NoticeBoardVO> selectList = clientService.selectNoticeBoard();
			List<NoticeBoardVO> searchList = clientService.searchNoticeBoard(cri);
			if (searchList != null && searchList.size() > 0) {
				if(selectList !=null && selectList.size()>0) {
					mv.addObject("noticeList",selectList);
				}
				mv.addObject("boardList", searchList);
			} else {
				mv.addObject("message", "????????? ????????? ????????????.");
			}
			pageMaker.setCri(cri);
			pageMaker.setTotalRowCount(clientService.searchNoticeBoardRows(cri));
			
			mv.setViewName("client/csCenter");
			return mv;
		}
		
		//???????????? ?????????
		@RequestMapping(value ="/csNoticeDetail")
		public ModelAndView csNoticeDetail (ModelAndView mv ,NoticeBoardVO vo) {					
			vo = clientService.selectDetailNoticeBoard(vo);
			if(vo!=null) {
				clientService.countUpNoticeBoard(vo);
				mv.addObject("noticeDetail", vo);
			}else {
				mv.addObject("message", "????????? ?????? ????????????.");
			}
			mv.setViewName("client/csNoticeDetail");
			return mv;
		}
	


		//??????????????? ?????????
		@RequestMapping(value ="/complainInsert")
		public ModelAndView complainInsert (HttpServletRequest request, ModelAndView mv ,ComplainBoardVO vo, @RequestParam("content") String content) {	
			
			if(clientService.complainInsert(vo)>0) {
				mv.addObject("success", "??????");
			}else {
				mv.addObject("message", "??????");
			}
			mv.setViewName("client/complainInsertComplete");

			return mv;
		}
		// ????????????????????? ??????
		@RequestMapping(value = "findIdPwf")
		public ModelAndView findIdPwf(ModelAndView mv) {
			String uri = "client/findIdPw";
			mv.setViewName(uri);
			return mv;
		}
		
		// ???????????????
		@RequestMapping(value = "findId")
		public ModelAndView findId(ModelAndView mv, ClientVO vo) {

			String clientName = vo.getClientName();
			vo = clientService.selectOnePhone(vo);
			if(vo!=null) {
				if(vo.getClientName().equals(clientName)==false) {
					mv.addObject("message", "??????????????? ?????? ???????????????. ?????? ??? ??????????????????.");
				}else {
					mv.addObject("clientId", vo.getClientId());
				}
			}else {
				mv.addObject("message", "??????????????? ????????????.");
			}
			mv.setViewName("jsonView");
			return mv;
		}

		// ???????????????
		@RequestMapping(value = "sendEmail")
		public ModelAndView sendEmailComplete(ModelAndView mv, ClientVO vo, EmailVO email) throws Exception {

		    StringBuffer temp = new StringBuffer();
		    
		    String[] code = {"!","@","#","$","%","^","&","*"};
		    Random rnd = new Random();
		    for (int i = 0; i < 8; i++) {
		        int rIndex = rnd.nextInt(4);
		        switch (rIndex) {
		        case 0:
		            // a-z
		            temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		            break;
		        case 1:
		            // A-Z
		            temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		            break;
		        case 2:
		            // 0-9
		            temp.append((rnd.nextInt(10)));
		            break;
		    	case 3:
		    		temp.append(code[(rnd.nextInt(8))]);
		    		break;
		    	}
		    }
		    System.out.println(temp);
		    System.out.println(vo.getClientEmail());
		    vo = clientService.selectOne(vo);
		    vo.setClientPassword(passwordEncoder.encode(temp));
		    
		    if(clientService.updateClientPw(vo)>0) {
		    	mv.addObject("message", "???????????? ?????? ??????");
		    }else {
		    	mv.addObject("message", "???????????? ?????? ??????");
		    	System.out.println("????????????????????????");
		    }
		    String reciver = vo.getClientEmail(); // ??????????????? ??????????????????.-> naver nate ??????
			String subject = "?????????????????? ?????? ???????????????????????????.";
			String content = "???????????????????????????. ???????????? ?????? ??????????????? "+temp+"?????????.";

			email.setReciver(reciver);
			email.setSubject(subject);
			email.setContent(content);
			sendService.SendEmail(email);
			mv.setViewName("jsonView");
			return mv;
		}
	
		
		// ????????????????????? ??????
		@RequestMapping(value = "sendEmailComplete")
		public ModelAndView sendEmailComplete(ModelAndView mv) {
			String uri = "client/sendEmailComplete";
			mv.setViewName(uri);
			return mv;
		}
		
		
		
		// ??????????????? ??????
		@RequestMapping(value = "clientMyInfo")
		public ModelAndView clientMyInfo(ModelAndView mv ,HttpSession session , SearchCriteria cri, PageMaker pageMaker,
				 OrderListVO orderListVo, ClientVO vo) {
			String uri ="";
			if(session.getAttribute("clientLoginID")!=null) {
				String loginID = (String) session.getAttribute("clientLoginID");
				orderListVo.setClientId(loginID);
				cri.setSnoEno();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("clientId", loginID);
				params.put("cri", cri);
				
				
				List<OrderListVO> list = orderService.searchClientIdOrderList(params);
				pageMaker.setCri(cri);
				pageMaker.setTotalRowCount(orderService.searchClientIdOrderRows(params));
				mv.addObject("orderList", list);
				
				uri = "client/clientMyInfo";
			}else {
				uri="client/pizzaMain";
			}
			
			mv.setViewName(uri);
			return mv;
		}
		
		// ??????????????????
		@RequestMapping(value = "orderDetail")
		public ModelAndView orderDetail(ModelAndView mv	, OrderListVO ordervo ) {
			ordervo = orderService.selectOneOrderList(ordervo);
			
			List<OrderDetailListVO> list = orderService.selectDetailbyOrderNumber(ordervo.getOrderNumber());
			if (list != null) {
				mv.addObject("orderInfo", ordervo);
				mv.addObject("list", list);
			} else {
				mv.addObject("message", "??????????????? ????????????.");
			}
			String uri = "client/orderDetail";
			mv.setViewName(uri);
			return mv;
		}
		
		//??????????????????
		@RequestMapping(value = "passwordCheck")
		public ModelAndView passwordCheck(ModelAndView mv, HttpSession session,ClientVO vo) {
			vo.setClientId((String)session.getAttribute("clientLoginID"));
			String password = vo.getClientPassword();
			
			vo = clientService.selectOne(vo);
			if(vo!=null) {
				if(passwordEncoder.matches(password, vo.getClientPassword())) {
					mv.addObject("success", "success");
				}else {
					mv.addObject("success", "fail");
				}
			}else {
				mv.addObject("success", "fail");
			}
			mv.setViewName("jsonView");
			return mv;
		}
		//??????????????????
		@RequestMapping(value = "myinfoUp")
		public ModelAndView myinfoUp(ModelAndView mv, HttpSession session,ClientVO vo) {
			vo.setClientId((String)session.getAttribute("clientLoginID"));
			
			vo = clientService.selectOne(vo);
			if(vo!=null) {
				mv.addObject("clientVO", vo);
			}else {
				mv.addObject("message", "????????? ????????? ????????????.");
			}
			mv.setViewName("client/myInfoUpdate");
			return mv;
		}
		
		//??????????????? ??????
		@RequestMapping(value = "infoUpsendSms")
		public ModelAndView infoUpsendSms(ModelAndView mv, ClientVO vo) {
			
			String phoneNumber = vo.getClientPhone();

			Random rand = new Random();
			String numStr = "";
			for (int i = 0; i < 6; i++) {
				String ran = Integer.toString(rand.nextInt(10));
				numStr += ran;
			}
			System.out.println("????????? ?????? : " + phoneNumber);
			System.out.println("???????????? : " + numStr);
			sendService.certifiedPhoneNumber(phoneNumber, numStr);
			mv.addObject("numStr", numStr);

			mv.setViewName("jsonView");
			return mv;
		}
		
		//??????????????????
		@RequestMapping(value = "myPasswordUpdate")
		public ModelAndView myPasswordUpdate(ModelAndView mv, HttpSession session,ClientVO vo) {
			vo.setClientId((String)session.getAttribute("clientLoginID"));
			log.info(vo.getClientPassword());
			
			vo.setClientPassword(passwordEncoder.encode(vo.getClientPassword()));
			
			if(clientService.updateClientPw(vo)>0) {
				mv.addObject("success", "success");
			}else {
				mv.addObject("success", "fail");
			}
			mv.setViewName("jsonView");
			return mv;
		}
		
		
		
		// ????????? ??????
		@RequestMapping(value = "myInfoUpdate")
		public ModelAndView myInfoUpdate(ModelAndView mv, HttpSession session,ClientVO vo) {
			vo.setClientId((String)session.getAttribute("clientLoginID"));
			
			if(clientService.updateMyInfo(vo)>0) {
				mv.addObject("success", "success");
			}else {
				mv.addObject("success", "fail");
			}
			mv.setViewName("jsonView");
			return mv;
		}

		// ????????????
		@RequestMapping(value = "deleteClient")
		public ModelAndView deleteClient(ModelAndView mv, HttpSession session, ClientVO vo) {
			vo.setClientId((String) session.getAttribute("clientLoginID"));
			String deleteReason = vo.getDeleteReason();
			
			
			
			vo = clientService.selectOne(vo);
			vo.setDeletePhone(vo.getClientPhone());
			vo.setDeleteReason(deleteReason);
			
			if (clientService.deleteClient(vo) > 0) {
				mv.addObject("success", "success");
			} else {
				mv.addObject("success", "fail");
			}
			mv.setViewName("jsonView");
			return mv;
		}
		
		
		
		
		/* ============================={ ????????? ????????? }================================ */
		

		// ????????? ????????? ??????
		@RequestMapping(value = "csEventf")
		public ModelAndView csEventf(ModelAndView mv, SearchCriteria cri, PageMaker pageMaker, @RequestParam("flag") String flag) {
			
			
			cri.setSnoEno();
			
			if(flag.equals("doing")) {
			List<EventBoardVO> searchList = clientService.searchEventBoard(cri);			
			if (searchList != null && searchList.size() > 0) {
				mv.addObject("eventList", searchList);
				mv.addObject("flag","doing");
			} else {
				mv.addObject("message", "????????? ????????? ????????????.");
			}
			pageMaker.setCri(cri);
			pageMaker.setTotalRowCount(clientService.searchEventBoardRows(cri));
			
			}else {
				List<EventBoardVO> searchList = clientService.searchEndEventBoard(cri);			
				if (searchList != null && searchList.size() > 0) {
					mv.addObject("eventList", searchList);
					mv.addObject("flag","end");
				} else {
					mv.addObject("message", "????????? ????????? ????????????.");
				}
				pageMaker.setCri(cri);
				pageMaker.setTotalRowCount(clientService.searchEndEventBoardRows(cri));
			}
			
			mv.setViewName("client/csEvent");
			return mv;
		}
		
		// ????????? ????????? ?????????
		@RequestMapping(value ="/csEventDetail")
		public ModelAndView csEventDetail (ModelAndView mv, EventBoardVO vo) {					
			vo = headOfficeService.selectDetailEventBoard(vo);
			if(vo!=null) {
				mv.addObject("eventDetail", vo);
			}else {
				mv.addObject("message", "????????? ?????? ????????????.");
			}
			mv.setViewName("client/csEventDetail");
			return mv;
		}

		
}// clientController
