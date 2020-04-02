package cc.vant.seckillmall.pojo.user.rsp;

import cc.vant.seckillmall.model.Favorites;
import lombok.Data;

import java.util.List;

@Data
public class ViewFavoritesRsp {

    private List<Favorites> favoritesList;
}
