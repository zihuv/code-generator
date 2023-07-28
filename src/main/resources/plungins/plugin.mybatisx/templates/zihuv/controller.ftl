package ${baseInfo.packageName};

import ${tableClass.fullClassName};
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${tableClass.remark} Controller
 *
 * @author zihuv
 * @since ${.now?string('yyyy-MM-dd')}
*/
@RestController
@RequestMapping("/${tableClass.tableName}")
public class ${baseInfo.fileName} {

}
