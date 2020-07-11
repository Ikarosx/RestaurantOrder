from datetime import datetime
import os
# 包名
package = ''
# 主键类型
pkType = ''
date = ''

def writeFile(content, filePath):
    if(os.path.exists(filePath)):
        filePath += '.bak'
    with open (filePath, 'w', encoding='utf-8') as f:
        f.write(content)
def firstUp(str):
  return str[0:1].upper() + str[1:]
def generateController(name):
    Name = firstUp(name)
    content = f'''
    package {package}.controller;
    import {package}.entity.{Name};
import {package}.exception.ResponseResult;
import {package}.entity.param.{Name}QueryParam;
/**
 * @author Ikaros
 * @date {date}
 */
public interface {Name}Controller {{

  ResponseResult insert{Name}({Name} {name});

  ResponseResult delete{Name}ById({pkType} id);

  ResponseResult update{Name}({pkType} id, {Name} {name});

  ResponseResult get{Name}ById({pkType} id);

  ResponseResult list{Name}sByPage(int page, int size, {Name}QueryParam {name}QueryParam);

  ResponseResult listAll{Name}s();
}}
    '''
    writeFile(content, f'./controller/{Name}Controller.java')
    
def generateControllerImpl(name):
    Name = firstUp(name)
    content = f'''
    package {package}.controller.impl;
import {package}.controller.{Name}Controller;
import {package}.entity.{Name};
import {package}.exception.ResponseResult;
import {package}.service.{Name}Service;
import {package}.entity.param.{Name}QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * @author Ikaros
 * @date {date}
 */
@RestController
@RequestMapping("{name}s")
public class {Name}ControllerImpl implements {Name}Controller {{
  @Autowired private {Name}Service {name}Service;

  @Override
  @PostMapping
  public ResponseResult insert{Name}(@Validated @RequestBody {Name} {name}) {{
    return {name}Service.insert{Name}({name});
  }}

  @Override
  @DeleteMapping("/{{id}}")
  public ResponseResult delete{Name}ById(@PathVariable {pkType} id) {{
    return {name}Service.delete{Name}ById(id);
  }}

  @Override
  @PutMapping("/{{id}}")
  public ResponseResult update{Name}(@PathVariable {pkType} id, @Validated @RequestBody {Name} {name}) {{
    return {name}Service.update{Name}({name});
  }}

  @Override
  @GetMapping("/{{id}}")
  public ResponseResult get{Name}ById(@PathVariable {pkType} id) {{
    return {name}Service.get{Name}ById(id);
  }}

  @Override
  @GetMapping("/{{page}}/{{size}}")
  public ResponseResult list{Name}sByPage(
    @PathVariable int page,@PathVariable int size,{Name}QueryParam {name}QueryParam) {{
    if (page < 1) {{
      page = 1;
    }}
    if (size <= 0) {{
      size = 10;
    }}
    if ({name}QueryParam == null) {{
      {name}QueryParam = new {Name}QueryParam();
    }}
    return {name}Service.list{Name}sByPage(page, size, {name}QueryParam);
  }}

  @Override
  @GetMapping
  public ResponseResult listAll{Name}s() {{
    return {name}Service.listAll{Name}s();
  }}
}}
    '''
    writeFile(content, f'./controller/impl/{Name}ControllerImpl.java')
def generateService(name):
    Name = firstUp(name)
    content =f'''
    package {package}.service;
    import {package}.entity.{Name};
    import {package}.entity.param.{Name}QueryParam;
import {package}.exception.ResponseResult;
/**
 * @author Ikaros
 * @date {date}
 */
public interface {Name}Service {{
  ResponseResult insert{Name}( {Name} {name});

  ResponseResult delete{Name}ById({pkType} id);

  ResponseResult update{Name}({Name} {name});

  ResponseResult get{Name}ById({pkType} id);

  ResponseResult list{Name}sByPage(int page, int size,  {Name}QueryParam {name}QueryParam);

  ResponseResult listAll{Name}s();
}}
    '''
    writeFile(content, f'./service/{Name}Service.java')
def generateServiceImpl(name):
    Name = firstUp(name)
    content =f'''
    package {package}.service.impl;
    import {package}.dao.{Name}Repository;
import {package}.entity.{Name};
import {package}.exception.CommonCodeEnum;
import {package}.exception.ExceptionCast;
import {package}.exception.ResponseResult;
import {package}.entity.param.{Name}QueryParam;
import {package}.service.{Name}Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
    import java.util.List;
    import java.util.Optional;
/**
 * @author Ikaros
 * @date {date}
 */
@Service("{Name}Service")
public class {Name}ServiceImpl implements {Name}Service {{

  @Autowired private {Name}Repository {name}Repository;

  @Override
  public ResponseResult insert{Name}({Name} {name}) {{
    {name}Repository.save({name});
    return CommonCodeEnum.SUCCESS;
  }}

  @Override
  public ResponseResult delete{Name}ById({pkType} id) {{
    {name}Repository.deleteById(id);
    return CommonCodeEnum.SUCCESS;
  }}

  @Override
  public ResponseResult update{Name}({Name} {name}) {{
    {name}Repository.save({name});
    return CommonCodeEnum.SUCCESS;
  }}

  @Override
  public ResponseResult get{Name}ById({pkType} id) {{
    Optional<{Name}> optional = {name}Repository.findById(id);
    {Name} {name} = optional.orElse(null);
    if ({name} == null) {{
      ExceptionCast.cast(CommonCodeEnum.DATA_NOT_FOUND);
    }}
    return CommonCodeEnum.SUCCESS.addData("{name}", {name});
  }}
  @Override
  public ResponseResult list{Name}sByPage(
      int page, int size, {Name}QueryParam {name}QueryParam) {{
    {Name} {name} = new {Name}();
    BeanUtils.copyProperties({name}QueryParam, {name});
    // 筛选
    ExampleMatcher matcher =
        ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example<{Name}> example = Example.of({name}, matcher);
    // 分页
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<{Name}> {name}Page = {name}Repository.findAll(example, pageable);
    return CommonCodeEnum.SUCCESS.addData("list", {name}Page.getContent()).addData("total", {name}Page.getTotalElements()).addData("totalPage", {name}Page.getTotalPages());
  }}

  @Override
  public ResponseResult listAll{Name}s() {{
    List<{Name}> list = {name}Repository.findAll();
    return CommonCodeEnum.SUCCESS.addData("list", list).addData("total", list.size());
  }}
}}
    '''
    writeFile(content, f'./service/impl/{Name}ServiceImpl.java')
def generateDao(name):
    Name = firstUp(name)
    content = f'''
    package {package}.dao;
import {package}.entity.{Name};
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Ikaros
 * @date {date}
 */
public interface {Name}Repository extends JpaRepository<{Name}, {pkType}> {{}}

    '''
    writeFile(content, f'./dao/{Name}Repository.java')

def generateEntityParam(name):
  Name = firstUp(name)
    content = f'''
    package {package}.entity.param;
import lombok.Data;
/**
 * @author Ikaros
 * @date {date}
 */
 @Data
public class {Name}QueryParam {{}}

    '''
    writeFile(content, f'./dao/{Name}Repository.java')
def generate(name):
    generateController(name)
    generateControllerImpl(name)
    generateService(name)
    generateServiceImpl(name)
    generateDao(name)
    generateEntityParam(name)
def checkBaseDirectory():
  dirs = [
    './controller',
    './controller/impl',
    './dao',
    './service',
    './service/impl',
    './entity',
    './entity/param'
  ]
  for dir in dirs:
    if not os.path.exists(dir):
      os.makedirs(dir)
if __name__ == '__main__':
    package = input('请输入package\n')
    checkBaseDirectory()
    while True:
        name = input('请输入要一键生成Controller、Service、Dao层的文件名，如要生成StudentController，请输入student，首字母小写，其他驼峰(exit退出)\n')
        if name == 'exit':
            exit(0)
        name = name.strip()
        if name == '':
            continue
        pkType = input('请输入主键类型\n')
        date = datetime.now().strftime('%Y/%m/%d %H:%M')
        generate(name)
