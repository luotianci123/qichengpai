package com.qcp.dfv.net;


import com.qcp.dfv.BuildConfig;

/**
 * Created by w.gs on 2016/10/27.
 */

public interface UrlConstant {

    /**
     * 登录退出
     */
    //登录
    String POST_USER_LOGIN = BuildConfig.API_URL + "user/login";
    //退出
    String POST_USER_EXIT = BuildConfig.API_URL + "user/exit";

    /**
     * 订单管理
     */
    //订单分页
    String POST_ORDER_ORDERPAGE = BuildConfig.API_URL + "order/OrderPage";
    //订单详情
    String POST_ORDER_ORDERDETAILPAGE = BuildConfig.API_URL + "order/OrderDetailPage";
    //订单查询
    String POST_ORDER_GETINDENTBYNO = BuildConfig.API_URL + "order/getIndentByNo";
    //取消订单
    String POST_ORDER_DELETENEWORDER = BuildConfig.API_URL + "order/deleteNewOrder";
    //申请退货
    String POST_ORDER_APPLYSENDBACK = BuildConfig.API_URL + "order/applySendBack";
    //确认收货
    String POST_ORDER_CONFIRMRECEIPT = BuildConfig.API_URL + "order/confirmReceipt";
    //添加填写退货物流信息
    String POST_ORDER_INSERINDENTRETURN = BuildConfig.API_URL + "order/insertIndentReturn";

    /**
     * 产品管理
     */
    //产品分页查询
    String POST_PRODUCT_PRODUCTPAGE = BuildConfig.API_URL + "product/productPage";
    //图片url
    String GET_PRODUCT_READPRODUCTIMAGE = BuildConfig.API_URL + "product/readProductImage?imagePath=";
    //获取产品配置
    String POST_PRODUCT_PRODUCTCONFIG = BuildConfig.API_URL + "product/ProductConfig";
    //添加购物车产品
    String POST_ORDER_ADDSHOPCAR = BuildConfig.API_URL + "order/addShopCar";
    //获取购物车详情
    String POST_ORDER_GETSHOPCARINFOVO = BuildConfig.API_URL + "order/getShopCarInfoVo";
    //提交订单
    String POST_ORDER_APPLYINTENT = BuildConfig.API_URL + "order/applyIndent";
    //产品搜索
    String POST_PRODUCT_FINDPRODUCT = BuildConfig.API_URL + "product/findProduct";
    //删除购物车产品
    String DELETE_ORDER_DELETESHOPCARINFO = BuildConfig.API_URL + "order/deleteShopCarInfo";

    /**
     * 我的
     */
    //新增收货地址
    String POST_MY_ADDPARTNERADDRESS = BuildConfig.API_URL + "my/addPartnerAddress";
    //获取归属人基本信息
    String GET_MY_GETBASICMESSAGE = BuildConfig.API_URL + "my/getBasicMessage";
    //我的激活量
    String GET_MY_GETACTIVEUSERCOUNT = BuildConfig.API_URL + "my/getActiveUserCount";
    //删除收货地址
    String POST_MY_DELETEPARTNERADDRESS = BuildConfig.API_URL + "my/deletePartnerAddress";
    //我的返现
    String GET_MY_GETCASHBACK = BuildConfig.API_URL + "my/getCashback";
    //获取收货地址
    String GET_MY_GETPARTNERADDRESS = BuildConfig.API_URL + "my/getPartnerAddress";
    //修改归属人基本信息
    String POST_MY_UPDATEBASICMESSAGE = BuildConfig.API_URL + "my/updateBasicMessage";
    //编辑收货地址
    String POST_MY_UPDATEPARTNERADDRESS = BuildConfig.API_URL + "my/updatePartnerAddress";
    //修改密码
    String POST_MY_UPDATEPASSWORD = BuildConfig.API_URL + "my/updatePassword";
    //版本升级
    String GET_MY_GETVERSION = BuildConfig.API_URL + "my/getVersion";
    //意见反馈
    String POST_MY_SETFEEDBACK = BuildConfig.API_URL + "my/setFeedback";


    //激活用户查询
    String POST_ACTIVITY_RESEARCH = BuildConfig.API_URL + "car/activequery";
    //车机详情
    String POST_ACTIVITY_STOREDETAIL = BuildConfig.API_URL + "car/storeDetails";
    //个人激活
    String POST_PERSION_ACTIVATION = BuildConfig.API_URL + "car/active";
    //企业激活
    String POST_COMPARY_ACTIVATION = BuildConfig.API_URL + "car/activeDevEnterprise";
    //车机流量查询
    String GET_TRAFFIC_QUERY = BuildConfig.API_URL + "tel/networkQuery";
    //门店全部
    String POST_STORES_ALL = BuildConfig.API_URL + "shop/shopsShow";
    //门店搜索
    String POST_STORES_SEARCH = BuildConfig.API_URL + "shop/searchShops";
    //车机解绑
    String POST_UNBAND_DEVSTORE = BuildConfig.API_URL + "car/unBindDevStore";
    //车机广告
    String POST_UPLOAD_PICTURE = BuildConfig.API_URL + "car/uploadPicture";
    //新建门店
    String POST_ADD_NEW_PSTORE = BuildConfig.API_URL + "shop/createShop";
    //修改门店
    String POST_MODIFY_SHOP = BuildConfig.API_URL + "shop/modifyShop";
    //启动、禁止
    String POST_MODIFY_STATUS = BuildConfig.API_URL + "shop/modifyStatus";
}
