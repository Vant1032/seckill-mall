package cc.vant.seckillmall.pojo.user.req;

import cc.vant.seckillmall.model.ReceiveAddress;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateReceiveAddressReq {
    @NotBlank
    private String receiverName;

    @NotBlank
    private String province;

    @NotBlank
    private String city;

    @NotBlank
    private String county;

    @NotBlank
    private String detailAddr;

    @NotBlank
    private String tel;

    private Boolean defaultAddr;


    public ReceiveAddress toReceiveAddress() {
        ReceiveAddress receiveAddress = new ReceiveAddress();
        receiveAddress.setReceiverName(this.receiverName);
        receiveAddress.setProvince(this.province);
        receiveAddress.setCity(this.city);
        receiveAddress.setCounty(this.county);
        receiveAddress.setDetailAddr(this.detailAddr);
        receiveAddress.setTel(this.tel);
        receiveAddress.setDefaultAddr(this.defaultAddr);

        // Not mapped ReceiveAddress fields:
        // addrId
        // userId
        // createdTime
        // updatedTime
        return receiveAddress;
    }
}
