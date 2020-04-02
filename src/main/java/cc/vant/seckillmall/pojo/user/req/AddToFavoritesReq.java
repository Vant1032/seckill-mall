package cc.vant.seckillmall.pojo.user.req;

import cc.vant.seckillmall.model.Favorites;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddToFavoritesReq {
    @NotNull
    private Integer userId;

    @NotNull
    private Integer goodsId;


    public Favorites toFavorites() {
        Favorites favorites = new Favorites();
        favorites.setUserId(this.userId);
        favorites.setGoodsId(this.goodsId);

        // Not mapped Favorites fields:
        // favId
        // status
        // createdTime
        // updatedTime
        return favorites;
    }
}
