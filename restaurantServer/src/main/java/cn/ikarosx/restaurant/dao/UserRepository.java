package cn.ikarosx.restaurant.dao;

import cn.ikarosx.restaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Ikaros
 * @date 2020/07/07 22:43
 */
public interface UserRepository extends JpaRepository<User, String> {}
