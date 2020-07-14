package cn.ikarosx.restaurant.controller.impl;

import cn.ikarosx.restaurant.aspect.NeedAdmin;
import cn.ikarosx.restaurant.controller.MenuTypeController;
import cn.ikarosx.restaurant.entity.MenuType;
import cn.ikarosx.restaurant.entity.param.MenuTypeQueryParam;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.MenuTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
@RestController
@RequestMapping("/menu/type")
public class MenuTypeControllerImpl implements MenuTypeController {
  @Autowired private MenuTypeService menuTypeService;

  @Override
  @PostMapping
  @NeedAdmin
  public ResponseResult insertMenuType(@Validated @RequestBody MenuType menuType) {
    return menuTypeService.insertMenuType(menuType);
  }

  @Override
  @DeleteMapping("/{id}")
  @NeedAdmin
  public ResponseResult deleteMenuTypeById(@PathVariable String id) {
    return menuTypeService.deleteMenuTypeById(id);
  }

  @Override
  @PutMapping("/{id}")
  @NeedAdmin
  public ResponseResult updateMenuType(
      @PathVariable String id, @Validated @RequestBody MenuType menuType) {
    return menuTypeService.updateMenuType(menuType);
  }

  @Override
  @GetMapping("/{id}")
  @NeedAdmin
  public ResponseResult getMenuTypeById(@PathVariable String id) {
    return menuTypeService.getMenuTypeById(id);
  }

  @Override
  @GetMapping("/{page}/{size}")
  @NeedAdmin
  public ResponseResult listMenuTypesByPage(
      @PathVariable int page, @PathVariable int size, MenuTypeQueryParam menuTypeQueryParam) {
    if (page < 1) {
      page = 1;
    }
    if (size <= 0) {
      size = 10;
    }
    if (menuTypeQueryParam == null) {
      menuTypeQueryParam = new MenuTypeQueryParam();
    }
    return menuTypeService.listMenuTypesByPage(page, size, menuTypeQueryParam);
  }

  @Override
  @GetMapping("/all")
  public ResponseResult listAllMenuTypes() {
    return menuTypeService.listAllMenuTypes();
  }
}
