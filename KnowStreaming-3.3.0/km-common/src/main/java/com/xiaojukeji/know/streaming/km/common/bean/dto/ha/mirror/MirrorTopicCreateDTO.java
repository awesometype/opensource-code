package com.xiaojukeji.know.streaming.km.common.bean.dto.ha.mirror;

import com.xiaojukeji.know.streaming.km.common.bean.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zengqiao
 * @date 20/4/23
 */
@Data
@ApiModel(description="Topic镜像信息")
public class MirrorTopicCreateDTO extends BaseDTO {
    @Min(value = 0, message = "sourceClusterPhyId不允许为空，且最小值为0")
    @ApiModelProperty(value = "源集群ID", example = "3")
    private Long sourceClusterPhyId;

    @Min(value = 0, message = "destClusterPhyId不允许为空，且最小值为0")
    @ApiModelProperty(value = "目标集群ID", example = "3")
    private Long destClusterPhyId;

    @NotBlank(message = "topicName不允许为空串")
    @ApiModelProperty(value = "Topic名称", example = "mirrorTopic")
    private String topicName;

    @NotNull(message = "syncData不允许为空")
    @ApiModelProperty(value = "同步数据", example = "true")
    private Boolean syncData;

    @NotNull(message = "syncConfig不允许为空")
    @ApiModelProperty(value = "同步配置", example = "false")
    private Boolean syncConfig;
}
