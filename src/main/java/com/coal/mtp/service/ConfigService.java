package com.coal.mtp.service;

import com.coal.mtp.dto.Config;

public interface ConfigService {

	Config getConfig(Long teamId, boolean needToken);
}
