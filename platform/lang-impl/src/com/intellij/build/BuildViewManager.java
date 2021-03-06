// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.build;

import com.intellij.build.progress.BuildProgress;
import com.intellij.build.progress.BuildProgressDescriptor;
import com.intellij.build.progress.BuildRootProgressImpl;
import com.intellij.lang.LangBundle;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.registry.Registry;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Vladislav.Soroka
 */
public class BuildViewManager extends AbstractViewManager {

  public BuildViewManager(Project project) {
    super(project);
    if (Registry.is("gradle.build.errors.problems", true)) {
      @Nullable BuildViewProblemsService buildViewProblemsService = project.getService(BuildViewProblemsService.class);
      if (buildViewProblemsService != null) {
        buildViewProblemsService.listenToBuildView(this);
      }
    }
  }

  @NotNull
  @Override
  public String getViewName() {
    return LangBundle.message("tab.title.build.output");
  }


  @ApiStatus.Experimental
  public static BuildProgress<BuildProgressDescriptor> createBuildProgress(@NotNull Project project) {
    return new BuildRootProgressImpl(ServiceManager.getService(project, BuildViewManager.class));
  }
}
