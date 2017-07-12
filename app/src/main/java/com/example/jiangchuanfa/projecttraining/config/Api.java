package com.example.jiangchuanfa.projecttraining.config;

/**
 * Created by crest on 2017/7/6.
 * 本类用于存放本程序相关的api接口
 */

public class Api {


    //一、商店基本路径
    public static final String SHOP_BASE_URL = "http://mobile.iliangcang.com";

    //商店分类：总
    public static final String SHOP_CATEGORY_URL = SHOP_BASE_URL + "/goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    //商品分类路径：头部
    public static final String SHOP_HEAD_URL = SHOP_BASE_URL + "/goods/goodsShare?app_key=Android&cat_code=";
    //商品分类路径：尾部
    public static final String SHOP_TAIL_URL = "&count=10&coverId=1&page=1&sig=3D3968703BE211CC6D931C9D4F737FB4%7C290216330933368&v=1.0";
    //    http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0045&count=10&coverId=1&page=1&sig=3D3968703BE211CC6D931C9D4F737FB4%7C290216330933368&v=1.0
    //商店分类：家居
    public static final String SHOP_CLEANING_URL = SHOP_HEAD_URL + "0045" + SHOP_TAIL_URL;

    //商品分类：家具
    public static final String SHOP_FURNITURE_URL = SHOP_HEAD_URL + "0055" + SHOP_TAIL_URL;
    //商品分类：文具
    public static final String SHOP_STATIONERY_URL = SHOP_HEAD_URL + "0062" + SHOP_TAIL_URL;
    //商品分类：数码
    public static final String SHOP_DIGITAL_URL = SHOP_HEAD_URL + "0069" + SHOP_TAIL_URL;
    //商品分类：玩乐
    public static final String SHOP_FUN_URL = SHOP_HEAD_URL + "0077" + SHOP_TAIL_URL;
    //商品分类：厨卫
    public static final String SHOP_KITCHEN_AND_TOILET_URL = SHOP_HEAD_URL + "0082" + SHOP_TAIL_URL;
    //商品分类：美食
    public static final String SHOP_DELICACY_URL = SHOP_HEAD_URL + "0092" + SHOP_TAIL_URL;
    //商品分类：男装
    public static final String SHOP_MENS_WEAR_URL = SHOP_HEAD_URL + "0101" + SHOP_TAIL_URL;
    //商品分类：女装
    public static final String SHOP_LADIES_URL = SHOP_HEAD_URL + "0112" + SHOP_TAIL_URL;
    //商品分类：童装
    public static final String SHOP_CHILDRENS_CLOTHING_URL = SHOP_HEAD_URL + "0125" + SHOP_TAIL_URL;
    //商品分类：鞋包
    public static final String SHOP_SHOES_AND_BAGS_URL = SHOP_HEAD_URL + "0129" + SHOP_TAIL_URL;
    //商品分类：配饰
    public static final String SHOP_ACCESSORIES_URL = SHOP_HEAD_URL + "0141" + SHOP_TAIL_URL;
    //商品分类：美护
    public static final String SHOP_BEAUTY_CARE_URL = SHOP_HEAD_URL + "0154" + SHOP_TAIL_URL;
    //商品分类：户外
    public static final String SHOP_OUTDOORS_URL = SHOP_HEAD_URL + "0166" + SHOP_TAIL_URL;
    //商品分类：植物
    public static final String SHOP_BOTANY_URL = SHOP_HEAD_URL + "0172" + SHOP_TAIL_URL;
    //商品分类：图书
    public static final String SHOP_BOOKS_URL = SHOP_HEAD_URL + "0182" + SHOP_TAIL_URL;
    //商品分类：礼物
    public static final String SHOP_GIFT_URL = SHOP_HEAD_URL + "0190" + SHOP_TAIL_URL;
    //商品分类：推荐
    public static final String SHOP_RECOMMEND_URL = SHOP_HEAD_URL + "0198" + SHOP_TAIL_URL;
    //商品分类：艺术
    public static final String SHOP_ART_URL = SHOP_HEAD_URL + "0214" + SHOP_TAIL_URL;


    //商店所有元素集合
    public static final String[] SHOP_ALL_URL = {SHOP_CLEANING_URL, SHOP_FURNITURE_URL, SHOP_STATIONERY_URL, SHOP_DIGITAL_URL, SHOP_FUN_URL, SHOP_KITCHEN_AND_TOILET_URL,
            SHOP_DELICACY_URL, SHOP_MENS_WEAR_URL, SHOP_LADIES_URL, SHOP_CHILDRENS_CLOTHING_URL, SHOP_SHOES_AND_BAGS_URL, SHOP_ACCESSORIES_URL, SHOP_BEAUTY_CARE_URL,
            SHOP_OUTDOORS_URL, SHOP_BOTANY_URL, SHOP_BOOKS_URL, SHOP_GIFT_URL, SHOP_RECOMMEND_URL, SHOP_ART_URL};


//    //一、商店基本路径
//    public static final String SHOP_BASE_URL ="http://mobile.iliangcang.com";

    //商店品牌：总
    public static final String SHOP_BRAND_URL = SHOP_BASE_URL + "/brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
//
//    //商品分类路径：头部
//    public static final String SHOP_HEAD_URL = SHOP_BASE_URL+"Share?app_key=Android&cat_code=";
//    //商品分类路径：尾部
//    public static final String SHOP_TAIL_URL = "&count=10&coverId=1&page=1&sig=3D3968703BE211CC6D931C9D4F737FB4%7C290216330933368&v=1.0";
//


//
//    2.品牌页面的json数据
//    页面1的json数据<page可变>
//    http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0
//
//
//
//    点击进入之后的页面<brand_id为可变，根据页面的json中的brand_id来获取相应的数据>
//
//    http://mobile.iliangcang.com/brand/brandShopList?app_key=Android&brand_id=14&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0
//
//    准备购买的页面json数据<goods_id为可变变量 ，根据goods_id再来获取相应的数据>
//
//    http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id=34230&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0

    //商店首页：总
    public static final String SHOP_HOME_URL = SHOP_BASE_URL + "/goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";

//    http://mobile.iliangcang.com/goods/shopSpecial?app_key=Android&count=10&page=1&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0

    //商店专题：总
    public static final String SHOP_SPECIAL_URL = SHOP_BASE_URL + "/goods/shopSpecial?app_key=Android&count=10&page=1&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";


//    http://mobile.iliangcang.com/goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0
    //达人首页网址：

    public static final String EXPERT_ALL_URL = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=1&sig=BF287AF953103F390674E73DDA18CFD8|639843030233268&v=1.0";

//商品详情头部
    public static final String GOODS_DETAILS_HEAD = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id=";
//商品详情尾部
    public static final String GOOD_DETAILS_TAIL = "&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";


}
