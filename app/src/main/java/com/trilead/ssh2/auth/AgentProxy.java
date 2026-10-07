/*
 * Created by Cristian Gonzalez on 27/01/24 22:59
 *  Copyright (c) NetFree Mexico 2024 . All rights reserved.
 */

package com.trilead.ssh2.auth;

import java.util.Collection;

public interface AgentProxy {
    public Collection/*<AgentIdentity>*/ getIdentities();
}
