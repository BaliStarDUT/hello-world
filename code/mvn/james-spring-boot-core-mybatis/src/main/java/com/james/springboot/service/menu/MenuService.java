package com.james.springboot.service.menu;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxMenu.WxMenuButton;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Yang on 2017/7/6 0006 下午 2:26.
 */
public class MenuService {
    private static final MenuService menuService = new MenuService();
    private MenuService(){

    }

    @Bean
    public static MenuService getMenuService(){
        return menuService;
    }

    public void createMenu(){
        WxMenu wxMenu = new WxMenu();
        List<WxMenuButton> buttonList = new ArrayList<>();

        WxMenuButton yinxiao = new WxMenuButton();
        yinxiao.setType(WxConsts.BUTTON_CLICK);
        yinxiao.setKey("yinxiao");
        yinxiao.setName("音效");
    }
}
