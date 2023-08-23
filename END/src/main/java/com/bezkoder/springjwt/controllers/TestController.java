//package com.bezkoder.springjwt.controllers;
//
//import com.bezkoder.springjwt.models.ERole;
//import com.bezkoder.springjwt.models.Role;
//import com.bezkoder.springjwt.models.User;
//import com.bezkoder.springjwt.models2.User2;
//import com.bezkoder.springjwt.payload.response.MessageResponse;
//import com.bezkoder.springjwt.repository.RoleRepository;
//import com.bezkoder.springjwt.repository.UserRepository;
//import com.bezkoder.springjwt.repository2.UserRepository2;
//import com.bezkoder.springjwt.security.jwt.AuthEntryPointJwt;
//import com.bezkoder.springjwt.security.services.UserDetailsImpl;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.*;
//import java.security.Principal;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static com.bezkoder.springjwt.util.JWTDecoderUtil.decodeJWTToken_readOnyName;
//import static com.bezkoder.springjwt.util.JWTDecoderUtil.payload_readName;
//
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/test")
//public class TestController {
//
//    /**
//     * new update at 9:51AM - 27/4
//     */
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserRepository2 userRepository2;
//    @Autowired
//    RoleRepository roleRepository;
//
//
//    public static List<String> list_normal_person = new ArrayList<>();
//
//    /**
//     * new update at 10:27AM at 5/5
//     */
//    public static Boolean allow_function = false;
//    @Autowired
//    private static AuthEntryPointJwt unauthorizedHandler;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @GetMapping("/all")
//    public String allAccess() {
//        return "Public Content.";
//    }
//
//
//    @GetMapping("/function")
//    @PreAuthorize("hasRole('USER') && authentication.principal.function_name == 'function'")
//    public ResponseEntity<?> apiFunction() {
//        System.out.println("work here");
//        /**
//         * logic code
//         */
//        return ResponseEntity.ok().body(new MessageResponse("welcome to this function"));
//    }
//
//
//    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public String userAccess() {
//
//        return "User Content.";
//    }
//
//
//    @GetMapping("/mod")
//    @PreAuthorize("hasRole('MODERATOR')")
//    public String moderatorAccess() {
//        return "Moderator Board.";
//    }
//
//    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String adminAccess() {
//        return "Admin Board.";
//    }
//
//    @GetMapping("/setting")
//    @PreAuthorize("hasRole('USER') && authentication.function_name == 'function'")
//    public ResponseEntity<?> accessFunction(@RequestHeader Map<String, String> headers) throws IOException {
//        final String api_name = "function";
//
//        if (headers.get("accept").equals("swap")) {
//            List<String> list = new ArrayList<>();
//            for (User user : userRepository.findAll()) {
//                System.out.println(user.getFunction_name() + " " + "function");
//                if (user.getFunction_name().equals("null")) {
//                    list.add(user.getUsername());
//                } else {
//                    user.setFunction_name("null");
//                    userRepository.deleteById(user.getId());
//                    userRepository.save(user);
//                }
//            }
//            for (User user : userRepository.findAll()) {
//                for (int i = 0; i < list.size(); i++) {
//                    if (user.getUsername().equals(list.get(i))) {
//                        user.setFunction_name(api_name);
//                        userRepository.deleteById(user.getId());
//                        userRepository.save(user);
//                    }
//                }
//            }
//            return ResponseEntity.ok(new MessageResponse("Swap successfully !"));
//
//        } else if (headers.get("accept").equals("public")) {
//            for (User user : userRepository.findAll()) {
//                if (user.getFunction_name().equals("null")) {
//                    user.setFunction_name(api_name);
//                    userRepository.deleteById(user.getId());
//                    userRepository.save(user);
//                }
//            }
//            return ResponseEntity.ok(new MessageResponse("Public api function successfully !"));
//        }
//        return ResponseEntity.ok(new MessageResponse("Error"));
//    }
//
//    @GetMapping("/test1")
//    @PreAuthorize("hasRole('USER') && authentication.principal.username == 'user55'")
//    public ResponseEntity<?> accessApi(@RequestHeader Map<String, String> headers) throws IOException {
//        return ResponseEntity.ok(new MessageResponse("Public api function successfully !"));
//    }
//
//    @GetMapping("/GET-GIFT-EXP")
//    @PreAuthorize(("hasRole('DEFAULT_PLAYER')"))
//    public String String(HttpServletRequest request) {
//
//        int exp_user = 0;
//        exp_user += 200;
//
//        if (exp_user >= 800) {
//            if (request.isUserInRole("Binh Nhi")) {
//                return "Tang sung vinh vien cho tan binh";
//            }
//        }
//        return "Tang sung dung thu 7 ngay cho cac chien huu";
//
//    }
//    @GetMapping("/GET-GIFT-EXP")
//    @PreAuthorize(("hasRole('DEFAULT_PLAYER')"))
//    public String String1(HttpServletRequest request) {
//
//        int exp_user = 0;
//        exp_user += 200;
//
//        if (exp_user >= 800) {
//            if (request.isUserInRole("Binh Nhi")) {
//                return "Tang sung vinh vien cho tan binh";
//            }
//        }
//        return "Tang sung dung thu 7 ngay cho cac chien huu";
//
//    }
//
////                request.getUserPrincipal();
////                String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
//    @GetMapping("/test3")
//    @PreAuthorize(("hasRole('USER')"))
//    public String String3(Authentication request) {
//        return "Public Content.";
//    }
//
//
//    /**
//     * new update
//     *
//     * @param
//     * @return
//     */
//    /**
//     * dem dong soi toa nho anh trang
//     * than ta mot minh giua chon lao
//     * than than trach phan de lam chi
//     * khi phan nguoi von da lao dao
//     * quan co quan thi cho ai tren
//     * mac cho tieng keu thau muon troi
//     * cach mang khong mau cach mang gi
//     * chi co do mau moi thanh cong
//     * mac cho long dan ko thanh mot
//     * dang ta van cu ung dung di
//     * di dau ve dau lam chuyen gi
//     *
//     * @param principal
//     * @param authentication
//     * @return
//     */
//    @GetMapping("/username0")
////    @PreAuthorize("authentication.name == authentication.principal.username")
////    @PreAuthorize("authentication.name == 'user2@gmail.com'")
////    @PreAuthorize("#username == authentication.principal.username")
//    //@PreAuthorize("#id == authentication.principal.id")
//    @PreAuthorize("authentication.principal.function_name == 'function'")
//    public String userAccess2(Principal principal, Authentication authentication) {
//        return authentication.getName();
//    }
//
//    @RequestMapping(value = "/username1", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Principal principal) {
//        return principal.getName();
//    }
//
//    @RequestMapping(value = "/username2", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Authentication authentication) {
//        return authentication.getAuthorities().toString();
//    }
//
//    @RequestMapping(value = "/username3", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentDetails(Authentication authentication) {
//
//        return authentication.getCredentials().toString();
//    }
//
//
//    @RequestMapping(value = "/username5", method = RequestMethod.GET)
//    @ResponseBody
//    public String getDetails(Authentication authentication, Principal principal) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//
//        return userDetails.getEmail();
//    }
//}


/**
 * ----------
 */


//  String data = headers.get("accept").toString(); // lấy value trong key , ví dụ value trên postman là "a" thì biến này là "a"
//                for (User user : userRepository.findAll()) {
//                    if (user.getUsername().equals(data)) { // check có trùng tên trong spring jpa ko có thì được add và lưu
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        user.getRoles().add(modRole);
//                        userRepository.save(user);
//
//                        System.out.println("new person : " + data);
//
//                        for (int i = 0; i < list_normal_person.size(); i++) {
//                            if (list_normal_person.get(i).equals(data)) {
//                                list_normal_person.remove(i);
//                                FileWriter fileWriter = new FileWriter("file.txt");
//                                for (String string : list_normal_person) {
//                                    fileWriter.write(string + System.lineSeparator());
//                                }
//                                break;
//                            }
//                        }
//                        return ResponseEntity.badRequest().body(new MessageResponse("new person has mode role: " + data));
//                    }
//                }

// @GetMapping("/setting")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> accessFunction(@RequestHeader Map<String, String> headers,HttpServletRequest request) throws IOException {
//        headers.forEach((key, value) -> {
//        });
//        String token = headers.get("authorization").toString();
//        System.out.println(token);
//        token = token.replaceAll("Bearer", "");
//        token = token.replaceAll(" ", "");
//        System.out.println(token);
//        decodeJWTToken_readOnyName(token);
//
//        for (int i = 0; i < list_function.size(); i++) {
//            if (payload_readName.contains(list_function.get(i))) {
//                if (headers.get("accept").equals("allow")) {
//                    if (request.isUserInRole("MODERATOR")) {
//                        for (User user : userRepository.findAll()) {
//                            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                            user.getRoles().add(modRole);
//                            userRepository.save(user);
//                        }
//                    }
////                    allow_function = true;
////
////                    System.out.println("person in current session : " + person_in_current_session);
////                    System.out.println("user changed : " + list_function.get(i));
////                    System.out.println("flag : " + allow_function);
////
////                    FileWriter myWriter = new FileWriter("asd.txt");
////                    myWriter.write("true");
////                    myWriter.close();
//
//                    return ResponseEntity.ok(new MessageResponse("open api function successfully"));
//                } else if (headers.get("accept").equals("non allow")) {
//                    allow_function = false;
//
//                    System.out.println("person in current session : " + person_in_current_session);
//                    System.out.println("user changed : " + list_function.get(i));
//                    System.out.println("flag : " + allow_function);
//
//                    FileWriter myWriter = new FileWriter("file2.txt");
//                    myWriter.write("false");
//                    myWriter.close();
//
//                    return ResponseEntity.ok(new MessageResponse("close api function successfully"));
//                } else if (headers.get("if-match") != null) {
//                    String data = headers.get("if-match").toString();
//                    if (userRepository.existsByUsername(data)) {
//                        list_function.remove(data);
//                        FileWriter myWriter = new FileWriter("file.txt");
//                        for (String string : list_function) {
//                            myWriter.write(string + System.lineSeparator());
//                        }
//                        myWriter.close();
//                        System.out.println("delete person : " + data);
//                        return ResponseEntity.ok(new MessageResponse("successfully"));
//                    }
//                } else {
//                    String data = headers.get("accept").toString();
//                    if (userRepository.existsByUsername(data)) {
//
//                        list_function.add(data);
//
//                        FileWriter myWriter = new FileWriter("file.txt");
//                        for (String string : list_function) {
//                            myWriter.write(string + System.lineSeparator());
//                        }
//                        myWriter.close();
//
//                        System.out.println("new person : " + data);
//
//                        return ResponseEntity.ok(new MessageResponse("successfully"));
//                    } else {
//                        return ResponseEntity.badRequest().body(new MessageResponse("Error : unknow data"));
//                    }
//                }
//            }
//        }
//        return ResponseEntity.badRequest().body(new MessageResponse("Unknow data"));
//    }

