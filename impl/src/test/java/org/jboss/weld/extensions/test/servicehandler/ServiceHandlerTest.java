/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.extensions.test.servicehandler;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Stuart Douglas
 * 
 */
@RunWith(Arquillian.class)
public class ServiceHandlerTest
{

   @Inject
   HelloWorld helloWorld;

   @Inject
   GoodbyeWorld goodbyteWorld;

   @Inject
   DecoratedHelloWorld decoratedHelloWorld;

   @Deployment
   public static Archive<?> deploy()
   {
      return ShrinkWrap.create("test.jar", JavaArchive.class).addPackage(ServiceHandlerTest.class.getPackage());
   }

   @Test
   public void testProxiedInterface()
   {
      Assert.assertTrue(helloWorld.helloWorld().equals("helloWorld"));
   }

   @Test
   public void testProxiedAbstractClass()
   {
      Assert.assertTrue(goodbyteWorld.goodbyeWorld().equals("goodbyeWorld"));
      Assert.assertFalse(goodbyteWorld.otherMethod().equals("otherMethod"));
   }

   @Test
   public void testInjectionIntoHandler()
   {
      Assert.assertTrue(decoratedHelloWorld.decoratedHelloWorld().equals("-decoratedHelloWorld-"));
   }

}
