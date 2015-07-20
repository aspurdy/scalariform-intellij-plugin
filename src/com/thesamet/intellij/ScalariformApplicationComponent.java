package com.thesamet.intellij;

import com.intellij.openapi.components.*;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

@State(
    name = "ScalariformSettings",
    storages = {
        @Storage(id = "other", file = StoragePathMacros.APP_CONFIG + "/other.xml")
    }
)
public class ScalariformApplicationComponent implements Configurable, ApplicationComponent, PersistentStateComponent<ScalariformApplicationComponent> {
    private boolean alignParameters = true;
    private boolean alignArguments = true;
    private boolean alignSingleLineCase = true;
    private boolean compactControlReadability = false;
    private boolean compactStringConcatenation = false;
    private boolean doubleIndentClassDeclaration = true;
    private boolean formatXML = true;
    private boolean indentPackageBlocks = false;
    private boolean indentWithTabs = false;
    private boolean multilineScalaDocCommentsStartOnFirstLine = false;
    private boolean placeScalaDocAsteriskBeneathSecondAsterisk = true;
    private boolean preserveSpaceBeforeArguments = false;
    private boolean rewriteArrowSymbols = false;
    private boolean spaceBeforeColon = false;
    private boolean spaceInsideParenthesis = false;
    private boolean spacesWithinPatternBinders = true;
    private Integer alignSingleLineCaseStatementsMaxArrowIndent = 30;
    private Integer indentSpaces = 2;
    private boolean indentLocalDefs = false;
    private boolean spaceInsideBrackets = false;
    private boolean spacesAroundMultiImports = false;
    private boolean autoFormatOnSave = false;

    public ScalariformApplicationComponent() {
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "com.thesamet.intellij.ScalariformApplicationComponent";
    }

    public boolean isAlignParameters() {
        return alignParameters;
    }

    public void setAlignParameters(final boolean alignParameters) {
        this.alignParameters = alignParameters;
    }

    public boolean isAlignArguments() {
        return alignArguments;
    }

    public void setAlignArguments(final boolean alignArguments) {
        this.alignArguments = alignArguments;
    }

    public boolean isAlignSingleLineCase() {
        return alignSingleLineCase;
    }

    public void setAlignSingleLineCase(final boolean alignSingleLineCase) {
        this.alignSingleLineCase = alignSingleLineCase;
    }

    public boolean isCompactControlReadability() {
        return compactControlReadability;
    }

    public void setCompactControlReadability(final boolean compactControlReadability) {
        this.compactControlReadability = compactControlReadability;
    }

    public boolean isCompactStringConcatenation() {
        return compactStringConcatenation;
    }

    public void setCompactStringConcatenation(final boolean compactStringConcatenation) {
        this.compactStringConcatenation = compactStringConcatenation;
    }

    public boolean isDoubleIndentClassDeclaration() {
        return doubleIndentClassDeclaration;
    }

    public void setDoubleIndentClassDeclaration(final boolean doubleIndentClassDeclaration) {
        this.doubleIndentClassDeclaration = doubleIndentClassDeclaration;
    }

    public boolean isFormatXML() {
        return formatXML;
    }

    public void setFormatXML(final boolean formatXML) {
        this.formatXML = formatXML;
    }

    public boolean isIndentPackageBlocks() {
        return indentPackageBlocks;
    }

    public void setIndentPackageBlocks(final boolean indentPackageBlocks) {
        this.indentPackageBlocks = indentPackageBlocks;
    }

    public boolean isIndentWithTabs() {
        return indentWithTabs;
    }

    public void setIndentWithTabs(final boolean indentWithTabs) {
        this.indentWithTabs = indentWithTabs;
    }

    public boolean isMultilineScalaDocCommentsStartOnFirstLine() {
        return multilineScalaDocCommentsStartOnFirstLine;
    }

    public void setMultilineScalaDocCommentsStartOnFirstLine(final boolean multilineScalaDocCommentsStartOnFirstLine) {
        this.multilineScalaDocCommentsStartOnFirstLine = multilineScalaDocCommentsStartOnFirstLine;
    }

    public boolean isPlaceScalaDocAsteriskBeneathSecondAsterisk() {
        return placeScalaDocAsteriskBeneathSecondAsterisk;
    }

    public void setPlaceScalaDocAsteriskBeneathSecondAsterisk(final boolean placeScalaDocAsteriskBeneathSecondAsterisk) {
        this.placeScalaDocAsteriskBeneathSecondAsterisk = placeScalaDocAsteriskBeneathSecondAsterisk;
    }

    public boolean isPreserveSpaceBeforeArguments() {
        return preserveSpaceBeforeArguments;
    }

    public void setPreserveSpaceBeforeArguments(final boolean preserveSpaceBeforeArguments) {
        this.preserveSpaceBeforeArguments = preserveSpaceBeforeArguments;
    }

    public boolean isRewriteArrowSymbols() {
        return rewriteArrowSymbols;
    }

    public void setRewriteArrowSymbols(final boolean rewriteArrowSymbols) {
        this.rewriteArrowSymbols = rewriteArrowSymbols;
    }

    public boolean isSpaceBeforeColon() {
        return spaceBeforeColon;
    }

    public void setSpaceBeforeColon(final boolean spaceBeforeColon) {
        this.spaceBeforeColon = spaceBeforeColon;
    }

    public boolean isSpaceInsideParenthesis() {
        return spaceInsideParenthesis;
    }

    public void setSpaceInsideParenthesis(final boolean spaceInsideParenthesis) {
        this.spaceInsideParenthesis = spaceInsideParenthesis;
    }

    public boolean isSpacesWithinPatternBinders() {
        return spacesWithinPatternBinders;
    }

    public void setSpacesWithinPatternBinders(final boolean spacesWithinPatternBinders) {
        this.spacesWithinPatternBinders = spacesWithinPatternBinders;
    }

    public Integer getAlignSingleLineCaseStatementsMaxArrowIndent() {
        return alignSingleLineCaseStatementsMaxArrowIndent;
    }

    public void setAlignSingleLineCaseStatementsMaxArrowIndent(final Integer alignSingleLineCaseStatementsMaxArrowIndent) {
        this.alignSingleLineCaseStatementsMaxArrowIndent = alignSingleLineCaseStatementsMaxArrowIndent;
    }

    public Integer getIndentSpaces() {
        return indentSpaces;
    }

    public void setIndentSpaces(final Integer indentSpaces) {
        this.indentSpaces = indentSpaces;
    }

    public boolean isSpacesAroundMultiImports() {
        return spacesAroundMultiImports;
    }

    public void setSpacesAroundMultiImports(final boolean spacesAroundMultiImports) {
        this.spacesAroundMultiImports = spacesAroundMultiImports;
    }

    public boolean isAutoFormatOnSave() {
        return autoFormatOnSave;
    }

    public void setAutoFormatOnSave(final boolean autoFormatOnSave) {
        this.autoFormatOnSave = autoFormatOnSave;
    }

    // Configurable
    ScalariformConfigurationForm form = null;

    @Nls
    @Override
    public String getDisplayName() {
        return "Scalariform";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (form == null) {
            form = new ScalariformConfigurationForm();
        }
        return form.getRootComponent();
    }

    @Override
    public boolean isModified() {
        return (form != null) && form.isModified(this);
    }

    @Override
    public void apply() throws ConfigurationException {
        if (form != null) {
            form.getData(this);
        }
    }

    @Override
    public void reset() {
        if (form != null) {
            form.setData(this);
        }
    }

    @Override
    public void disposeUIResources() {
        form = null;
    }

    public void loadState(ScalariformApplicationComponent state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public ScalariformApplicationComponent getState() {
        return this;
    }

    public boolean isIndentLocalDefs() {
        return indentLocalDefs;
    }

    public void setIndentLocalDefs(final boolean indentLocalDefs) {
        this.indentLocalDefs = indentLocalDefs;
    }

    public boolean isSpaceInsideBrackets() {
        return spaceInsideBrackets;
    }

    public void setSpaceInsideBrackets(final boolean spaceInsideBrackets) {
        this.spaceInsideBrackets = spaceInsideBrackets;
    }
}
