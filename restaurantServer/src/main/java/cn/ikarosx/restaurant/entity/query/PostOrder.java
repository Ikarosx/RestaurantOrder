package cn.ikarosx.restaurant.entity.query;

import cn.ikarosx.restaurant.entity.OrderDetail;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ikarosx
 * @date 2020/7/11 17:08
 */
@Data
public class PostOrder {
  @NotNull
  @Min(0)
  private Double sum;

  @NotNull private String  userId;
  @NotNull private List<OrderDetail> orderDetails;
}
