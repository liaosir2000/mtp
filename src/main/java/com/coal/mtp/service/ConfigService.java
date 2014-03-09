package com.coal.mtp.service;

import com.coal.mtp.dto.Config;

public interface ConfigService {

	Config getConfig(String teamId, boolean needToken);
}
