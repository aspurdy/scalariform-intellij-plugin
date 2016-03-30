package com.thesamet.intellij;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;

import javax.swing.*;

public class ScalariformSettingsPanel {
    private Project myProject;
    private JPanel rootComponent;
    private JCheckBox alignParametersCheckBox;
    private JCheckBox alignArgumentsCheckBox;
    private JCheckBox alignSingleLineCaseCheckBox;
    private JCheckBox compactControlReadabilityCheckBox;
    private JCheckBox compactStringConcatenationCheckBox;
    private JCheckBox doubleIndentClassDeclarationCheckBox;
    private JCheckBox formatXMLCheckBox;
    private JCheckBox indentPackageBlocksCheckBox;
    private JCheckBox indentWithTabsCheckBox;
    private JCheckBox multilineScalaDocCommentsStartCheckBox;
    private JCheckBox placeScalaDocAsterisksBeneathCheckBox;
    private JCheckBox preserveSpaceBeforeArgumentsCheckBox;
    private JCheckBox rewriteArrowSymbolsCheckBox;
    private JCheckBox spaceBeforeColonCheckBox;
    private JCheckBox spaceInsideParenthesisCheckBox;
    private JCheckBox spacesWithinPatternBindersCheckBox;
    private JCheckBox indentLocalDefsCheckBox;
    private JCheckBox spaceInsideBracketsCheckBox;
    private JTextField indentSpaces;
    private JTextField maxArrowIndent;
    private JCheckBox spacesAroundMultiImportsCheckBox;
    private JCheckBox autoFormatOnSaveCheckBox;

    public ScalariformSettingsPanel(Project project) {
        myProject = project;
    }

    public JComponent getPanel() {
        return rootComponent;
    }

    public boolean isModified() {
        ScalariformSettings settings = ScalariformSettings.getInstance(myProject);
        return alignParametersCheckBox.isSelected() != settings.isAlignParameters()
            || alignArgumentsCheckBox.isSelected() != settings.isAlignArguments()
            || alignSingleLineCaseCheckBox.isSelected() != settings.isAlignSingleLineCase()
            || compactControlReadabilityCheckBox.isSelected() != settings.isCompactControlReadability()
            || compactStringConcatenationCheckBox.isSelected() != settings.isCompactStringConcatenation()
            || doubleIndentClassDeclarationCheckBox.isSelected() != settings.isDoubleIndentClassDeclaration()
            || formatXMLCheckBox.isSelected() != settings.isFormatXML()
            || indentPackageBlocksCheckBox.isSelected() != settings.isIndentPackageBlocks()
            || indentWithTabsCheckBox.isSelected() != settings.isIndentWithTabs()
            || multilineScalaDocCommentsStartCheckBox.isSelected() != settings.isMultilineScalaDocCommentsStartOnFirstLine()
            || placeScalaDocAsterisksBeneathCheckBox.isSelected() != settings.isPlaceScalaDocAsteriskBeneathSecondAsterisk()
            || preserveSpaceBeforeArgumentsCheckBox.isSelected() != settings.isPreserveSpaceBeforeArguments()
            || rewriteArrowSymbolsCheckBox.isSelected() != settings.isRewriteArrowSymbols()
            || spaceBeforeColonCheckBox.isSelected() != settings.isSpaceBeforeColon()
            || spaceInsideParenthesisCheckBox.isSelected() != settings.isSpaceInsideParenthesis()
            || spacesWithinPatternBindersCheckBox.isSelected() != settings.isSpacesWithinPatternBinders()
            || (indentSpaces.getText() != null ? !indentSpaces.getText().equals(settings.getIndentSpaces().toString()) : settings.getIndentSpaces() != null)
            || (maxArrowIndent.getText() != null ? !maxArrowIndent.getText().equals(settings.getAlignSingleLineCaseStatementsMaxArrowIndent().toString()) : settings.getAlignSingleLineCaseStatementsMaxArrowIndent() != null)
            || indentLocalDefsCheckBox.isSelected() != settings.isIndentLocalDefs()
            || spaceInsideBracketsCheckBox.isSelected() != settings.isSpaceInsideBrackets()
            || spacesAroundMultiImportsCheckBox.isSelected() != settings.isSpacesAroundMultiImports()
            || autoFormatOnSaveCheckBox.isSelected() != settings.isAutoFormatOnSave()
            ;
    }

    public void apply() throws ConfigurationException {
        ScalariformSettings settings = ScalariformSettings.getInstance(myProject);
        settings.setAlignParameters(alignParametersCheckBox.isSelected());
        settings.setAlignArguments(alignArgumentsCheckBox.isSelected());
        settings.setAlignSingleLineCase(alignSingleLineCaseCheckBox.isSelected());
        settings.setCompactControlReadability(compactControlReadabilityCheckBox.isSelected());
        settings.setCompactStringConcatenation(compactStringConcatenationCheckBox.isSelected());
        settings.setDoubleIndentClassDeclaration(doubleIndentClassDeclarationCheckBox.isSelected());
        settings.setFormatXML(formatXMLCheckBox.isSelected());
        settings.setIndentPackageBlocks(indentPackageBlocksCheckBox.isSelected());
        settings.setIndentWithTabs(indentWithTabsCheckBox.isSelected());
        settings.setMultilineScalaDocCommentsStartOnFirstLine(multilineScalaDocCommentsStartCheckBox.isSelected());
        settings.setPlaceScalaDocAsteriskBeneathSecondAsterisk(placeScalaDocAsterisksBeneathCheckBox.isSelected());
        settings.setPreserveSpaceBeforeArguments(preserveSpaceBeforeArgumentsCheckBox.isSelected());
        settings.setRewriteArrowSymbols(rewriteArrowSymbolsCheckBox.isSelected());
        settings.setSpaceBeforeColon(spaceBeforeColonCheckBox.isSelected());
        settings.setSpaceInsideParenthesis(spaceInsideParenthesisCheckBox.isSelected());
        settings.setSpacesWithinPatternBinders(spacesWithinPatternBindersCheckBox.isSelected());
        try {
            settings.setIndentSpaces(Integer.parseInt(indentSpaces.getText()));
        } catch (NumberFormatException ignored) {
        }
        try {
            settings.setAlignSingleLineCaseStatementsMaxArrowIndent(Integer.parseInt(maxArrowIndent.getText()));
        } catch (NumberFormatException ignored) {
        }
        settings.setIndentLocalDefs(indentLocalDefsCheckBox.isSelected());
        settings.setSpaceInsideBrackets(spaceInsideBracketsCheckBox.isSelected());
        settings.setSpacesAroundMultiImports(spacesAroundMultiImportsCheckBox.isSelected());
        settings.setAutoFormatOnSave(autoFormatOnSaveCheckBox.isSelected());
    }

    public void reset() {
        ScalariformSettings settings = ScalariformSettings.getInstance(myProject);
        alignParametersCheckBox.setSelected(settings.isAlignParameters());
        alignArgumentsCheckBox.setSelected(settings.isAlignArguments());
        alignSingleLineCaseCheckBox.setSelected(settings.isAlignSingleLineCase());
        compactControlReadabilityCheckBox.setSelected(settings.isCompactControlReadability());
        compactStringConcatenationCheckBox.setSelected(settings.isCompactStringConcatenation());
        doubleIndentClassDeclarationCheckBox.setSelected(settings.isDoubleIndentClassDeclaration());
        formatXMLCheckBox.setSelected(settings.isFormatXML());
        indentPackageBlocksCheckBox.setSelected(settings.isIndentPackageBlocks());
        indentWithTabsCheckBox.setSelected(settings.isIndentWithTabs());
        multilineScalaDocCommentsStartCheckBox.setSelected(settings.isMultilineScalaDocCommentsStartOnFirstLine());
        placeScalaDocAsterisksBeneathCheckBox.setSelected(settings.isPlaceScalaDocAsteriskBeneathSecondAsterisk());
        preserveSpaceBeforeArgumentsCheckBox.setSelected(settings.isPreserveSpaceBeforeArguments());
        rewriteArrowSymbolsCheckBox.setSelected(settings.isRewriteArrowSymbols());
        spaceBeforeColonCheckBox.setSelected(settings.isSpaceBeforeColon());
        spaceInsideParenthesisCheckBox.setSelected(settings.isSpaceInsideParenthesis());
        spacesWithinPatternBindersCheckBox.setSelected(settings.isSpacesWithinPatternBinders());
        indentSpaces.setText(settings.getIndentSpaces().toString());
        maxArrowIndent.setText(settings.getAlignSingleLineCaseStatementsMaxArrowIndent().toString());
        indentLocalDefsCheckBox.setSelected(settings.isIndentLocalDefs());
        spaceInsideBracketsCheckBox.setSelected(settings.isSpaceInsideBrackets());
        spacesAroundMultiImportsCheckBox.setSelected(settings.isSpacesAroundMultiImports());
        autoFormatOnSaveCheckBox.setSelected(settings.isAutoFormatOnSave());
    }
}
