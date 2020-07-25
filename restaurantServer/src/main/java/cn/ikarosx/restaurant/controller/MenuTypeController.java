package cn.ikarosx.restaurant.controller;

import cn.ikarosx.restaurant.entity.MenuType;
import cn.ikarosx.restaurant.entity.query.MenuTypeQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
public interface MenuTypeController {

  ResponseResult insertMenuType(MenuType menuType);

  ResponseResult deleteMenuTypeById(String id);

  ResponseResult updateMenuType(String id, MenuType menuType);

  ResponseResult getMenuTypeById(String id);

  ResponseResult listMenuTypesByPage(int page, int size, MenuTypeQueryParam menuTypeQueryParam);

  ResponseResult listAllMenuTypes();
}
