package cn.ikarosx.restaurant.service.impl;

import cn.ikarosx.restaurant.dao.MenuRepository;
import cn.ikarosx.restaurant.entity.Menu;
import cn.ikarosx.restaurant.entity.query.MenuQueryParam;
import cn.ikarosx.restaurant.exception.CommonCodeEnum;
import cn.ikarosx.restaurant.exception.ExceptionCast;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

  @Autowired private MenuRepository menuRepository;

  @Override
  public ResponseResult insertMenu(Menu menu) {
    menuRepository.save(menu);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult deleteMenuById(String id) {
    menuRepository.deleteById(id);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult updateMenu(Menu menu) {
    menuRepository.save(menu);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult getMenuById(String id) {
    Optional<Menu> optional = menuRepository.findById(id);
    Menu menu = optional.orElse(null);
    if (menu == null) {
      ExceptionCast.cast(CommonCodeEnum.DATA_NOT_FOUND);
    }
    return CommonCodeEnum.SUCCESS.addData("menu", menu);
  }

  @Override
  public ResponseResult listMenusByPage(int page, int size, MenuQueryParam menuQueryParam) {
    Menu menu = new Menu();
    BeanUtils.copyProperties(menuQueryParam, menu);
    // 筛选
    ExampleMatcher matcher =
        ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example<Menu> example = Example.of(menu, matcher);
    // 分页
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<Menu> menuPage = menuRepository.findAll(example, pageable);
    return CommonCodeEnum.SUCCESS.addData(
        "list",
        menuPage.getContent(),
        "total",
        menuPage.getTotalElements(),
        "totalPage",
        menuPage.getTotalPages());
  }

  @Override
  public ResponseResult listAllMenus() {
    List<Menu> list = menuRepository.findAll();
    return CommonCodeEnum.SUCCESS.addData("list", list, "total", list.size());
  }
}
