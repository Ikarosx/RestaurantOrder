
    package cn.ikarosx.restaurant.service;
    import cn.ikarosx.restaurant.entity.Menu;
    import cn.ikarosx.restaurant.entity.query.MenuQueryParam;
    import cn.ikarosx.restaurant.exception.ResponseResult;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
public interface MenuService {
  ResponseResult insertMenu( Menu menu);

  ResponseResult deleteMenuById(String id);

  ResponseResult updateMenu(Menu menu);

  ResponseResult getMenuById(String id);

  ResponseResult listMenusByPage(int page, int size,  MenuQueryParam menuQueryParam);

  ResponseResult listAllMenus();
}
    