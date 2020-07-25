package cn.ikarosx.restaurant.controller.impl;

import cn.ikarosx.restaurant.aspect.NeedAdmin;
import cn.ikarosx.restaurant.controller.MenuController;
import cn.ikarosx.restaurant.entity.Menu;
import cn.ikarosx.restaurant.entity.query.MenuQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
@RestController
@RequestMapping("menu")
public class MenuControllerImpl implements MenuController {
  @Autowired private MenuService menuService;

  @Override
  @PostMapping
  @NeedAdmin
  public ResponseResult insertMenu(@Validated @RequestBody Menu menu) {
    return menuService.insertMenu(menu);
  }

  @Override
  @DeleteMapping("/{id}")
  @NeedAdmin
  public ResponseResult deleteMenuById(@PathVariable String id) {
    return menuService.deleteMenuById(id);
  }

  @Override
  @PutMapping("/{id}")
  @NeedAdmin
  public ResponseResult updateMenu(@PathVariable String id, @Validated @RequestBody Menu menu) {
    return menuService.updateMenu(menu);
  }

  @Override
  @GetMapping("/{id}")
  @NeedAdmin
  public ResponseResult getMenuById(@PathVariable String id) {
    return menuService.getMenuById(id);
  }

  @Override
  @GetMapping("/{page}/{size}")
  @NeedAdmin
  public ResponseResult listMenusByPage(
      @PathVariable int page, @PathVariable int size, MenuQueryParam menuQueryParam) {
    if (page < 1) {
      page = 1;
    }
    if (size <= 0) {
      size = 10;
    }
    if (menuQueryParam == null) {
      menuQueryParam = new MenuQueryParam();
    }
    return menuService.listMenusByPage(page, size, menuQueryParam);
  }

  @Override
  @GetMapping("/all")
  public ResponseResult listAllMenus() {
    return menuService.listAllMenus();
  }
}
