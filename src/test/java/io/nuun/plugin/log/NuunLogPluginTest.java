/**
 * Copyright (C) 2014 Kametic <epo.jemba@kametic.com>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * or any later version
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.nuun.plugin.log;

import static io.nuun.kernel.core.NuunCore.createKernel;
import static io.nuun.kernel.core.NuunCore.newKernelConfiguration;
import static org.fest.assertions.Assertions.assertThat;
import io.nuun.kernel.api.Kernel;
import io.nuun.plugin.log.sample.Holder;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 *
 * 
 * @author epo.jemba@kametic.com
 *
 */
public class NuunLogPluginTest {

	Injector injector;

	static Kernel underTest;
	
	
	@Before
	public void init ()  {
		
	  
	        underTest = createKernel(
	                //
	                newKernelConfiguration() //

	                );
		  
		  
		  
		  underTest.init();
		  underTest.start();
		  
		  Module aggregationModule = new AbstractModule()
	        {

	            @Override
	            protected void configure()
	            {
	                bind(Holder.class);

	            }
	        };
	        injector = underTest.objectGraph().as(Injector.class).createChildInjector(aggregationModule);
		
	}

	@Test
	public void logger_should_be_injected() {
		Holder holder = injector.getInstance(Holder.class);
		assertThat(holder.getLogger()).isNotNull();
		holder.getLogger().error("MESSAGE FROM LOGGER.");
	}

	@Test
	public void logger_should_be_injected_with_metaannotation() {
		Holder holder = injector.getInstance(Holder.class);
		assertThat(holder.getLogger2()).isNotNull();
		holder.getLogger().error("MESSAGE FROM LOGGER2.");
	}

}
