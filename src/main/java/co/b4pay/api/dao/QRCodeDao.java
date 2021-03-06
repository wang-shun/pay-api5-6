package co.b4pay.api.dao;

import co.b4pay.api.model.qrcode;
import com.google.zxing.qrcode.encoder.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 渠道信息DAO
 *
 * @author YK
 * @version $Id: ChannelDao.java, v 0.1 2018年4月21日 下午22:09:12 YK Exp $
 */
@Repository
public interface QRCodeDao extends JpaRepository<qrcode, Long> {

    @Query("FROM qrcode t WHERE t.status = :status")
    List<qrcode> findByStatus(@Param("status") int status);

    @Query("FROM qrcode t WHERE t.id = :id")
    QRCode findNoById(@Param("id") Long id);

    @Query("FROM qrcode t WHERE t.id= :id and t.status = :status")
    qrcode findByIdAndStatus(@Param("id") Long id, @Param("status") int status);

    @Query("FROM qrcode t WHERE t.channelId= :channelId and t.status = :status and t.codeType= :codeType and t.money= :money")
    List<qrcode> findByChannelIdAndStatusAndCodeTypeAndMoney(@Param("channelId") Long channelId, @Param("status") int status, @Param("codeType") int codeType, @Param("money") BigDecimal money);


    @Modifying
    @Transactional
    @Query("update qrcode t set t.turnover= :turnover")
    void updateTurnover(@Param("turnover") BigDecimal turnover);

    @Modifying
    @Transactional
    @Query("update qrcode t set t.no= :no where t.id= :id")
    int updateNo(@Param("id") Long id, @Param("no") int no);

    @Modifying
    @Transactional
    @Query("update qrcode t set t.status= :status where t.id= :id")
    int updateStatus(@Param("id") Long id, @Param("status") int status);




}