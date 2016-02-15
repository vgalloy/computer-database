package com.excilys.computerdatabase.dto.mapper.impl;

import com.excilys.computerdatabase.dto.mapper.RuleDtoMapper;
import com.excilys.computerdatabase.dto.model.RuleDto;
import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Galloy
 *         The Class RuleDtoMapperImpl.
 */
@Service
public class RuleDtoMapperImpl implements RuleDtoMapper {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public RuleDto mapFromModel(Rule model) {
        log.warn("mapFromModel : unsupportedOperation");
        throw new UnsupportedOperationException();
    }

    @Override
    public Rule mapToModel(RuleDto dto) {
        Rule rule = new Rule();
        rule.setAuthorized(dto.isAuthorized());
        rule.setRole(Role.build(dto.getRole()));
        rule.setUserName(dto.getUserName());
        return rule;
    }
}
