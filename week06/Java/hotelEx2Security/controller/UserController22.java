package hotelEx2Security.controller;


public class UserController22 {
//
//        private final UserDAO userDao;
//        private final TokenFactory tokenFactory = TokenFactory.getInstance();
//
//        public UserController() {
//            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
//            userDao = UserDAO.getInstance(emf);
//        }
//
//        public void login(Context ctx) /*throws ApiException,*/ AuthorizationException {
//            String[] userInfos = getUserInfos(ctx, true);
//            User user = getVerfiedOrRegisterUser(userInfos[0], userInfos[1], "", false);
//            String token = getToken(userInfos[0], user.getRolesAsStrings());
//
//            // Create response
//            ctx.status(200);
//            ctx.result(createResponse(userInfos[0], token));
//        }
//
//        public void register(Context ctx) throws ApiException, AuthorizationException {
//            String[] userInfos = getUserInfos(ctx, false);
//            User user = getVerfiedOrRegisterUser(userInfos[0], userInfos[1], userInfos[2], true);
//            String token = getToken(userInfos[0], user.getRolesAsStrings());
//
//            // Create response
//            ctx.res().setStatus(201);
//            ctx.result(createResponse(userInfos[0], token));
//        }
//
//        private String createResponse(String username, String token) {
//            ObjectMapper mapper = new ObjectMapper();
//            ObjectNode responseJson = mapper.createObjectNode();
//            responseJson.put("username", username);
//            responseJson.put("token", token);
//            return responseJson.toString();
//        }
//
//        private String[] getUserInfos(Context ctx, boolean tryLogin) throws ApiException {
//            String request = ctx.body();
//            return tokenFactory.parseJsonObject(request, tryLogin);
//        }
//
//        private User getVerfiedOrRegisterUser(String username, String password, String role, boolean isCreate) throws AuthorizationException {
//            return isCreate ? userDao.registerUser(username, password, role) : userDao.getVerifiedUser(username, password);
//        }
//
//        private String getToken(String username, Set<String> userRoles) throws ApiException {
//            return tokenFactory.createToken(username, userRoles);
//        }
//    }

}
