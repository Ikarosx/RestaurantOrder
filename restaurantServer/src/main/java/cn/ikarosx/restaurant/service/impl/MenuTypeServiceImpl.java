package cn.ikarosx.restaurant.service.impl;

import cn.ikarosx.restaurant.dao.MenuTypeRepository;
import cn.ikarosx.restaurant.entity.MenuType;
import cn.ikarosx.restaurant.entity.query.MenuTypeQueryParam;
import cn.ikarosx.restaurant.exception.CommonCodeEnum;
import cn.ikarosx.restaurant.exception.ExceptionCast;
import cn.ikarosx.restaurant.exception.ResponseResult;
import cn.ikarosx.restaurant.service.MenuTypeService;
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
@Service("MenuTypeService")
public class MenuTypeServiceImpl implements MenuTypeService {

  @Autowired private MenuTypeRepository menuTypeRepository;

  @Override
  public ResponseResult insertMenuType(MenuType menuType) {
    menuTypeRepository.save(menuType);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult deleteMenuTypeById(String id) {
    menuTypeRepository.deleteById(id);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult updateMenuType(MenuType menuType) {
    menuTypeRepository.save(menuType);
    return CommonCodeEnum.SUCCESS.clearData();
  }

  @Override
  public ResponseResult getMenuTypeById(String id) {
    Optional<MenuType> optional = menuTypeRepository.findById(id);
    MenuType menuType = optional.orElse(null);
    if (menuType == null) {
      ExceptionCast.cast(CommonCodeEnum.DATA_NOT_FOUND);
    }
    return CommonCodeEnum.SUCCESS.addData("menuType", menuType);
  }

  @Override
  public ResponseResult listMenuTypesByPage(
      int page, int size, MenuTypeQueryParam menuTypeQueryParam) {
    MenuType menuType = new MenuType();
    BeanUtils.copyProperties(menuTypeQueryParam, menuType);
    // 筛选
    ExampleMatcher matcher =
        ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example<MenuType> example = Example.of(menuType, matcher);
    // 分页
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<MenuType> menuTypePage = menuTypeRepository.findAll(example, pageable);
    return CommonCodeEnum.SUCCESS.addData(
        "list",
        menuTypePage.getContent(),
        "total",
        menuTypePage.getTotalElements(),
        "totalPage",
        menuTypePage.getTotalPages());
  }

  @Override
  public ResponseResult listAllMenuTypes() {
    List<MenuType> list = menuTypeRepository.findAll();
    return CommonCodeEnum.SUCCESS.addData("list", list, "total", list.size());
  }
}
