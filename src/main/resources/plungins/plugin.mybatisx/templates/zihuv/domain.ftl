package ${domain.packageName};

import java.io.Serializable;
<#list tableClass.importList as fieldType>${"\n"}import ${fieldType};</#list>
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("${tableClass.tableName}")
@ApiModel(description = "${tableClass.remark!}")
public class ${tableClass.shortClassName} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list tableClass.allFields as field>
    @ApiModelProperty("${field.remark!}")
    private ${field.shortTypeName} ${field.fieldName};

</#list>
}