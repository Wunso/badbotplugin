package net.runelite.client.plugins.bot;

import net.runelite.client.plugins.bot.base.Accordion;
import net.runelite.client.plugins.bot.base.BotViewPanel;
import net.runelite.client.plugins.bot.base.DebugPanel;
import net.runelite.client.plugins.bot.base.GeneralPanel;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.materialtabs.MaterialTab;
import net.runelite.client.ui.components.materialtabs.MaterialTabGroup;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BotPanel extends PluginPanel {
    private final JPanel display = new JPanel();
    private final MaterialTabGroup tabGroup = new MaterialTabGroup(display);


    @Inject
    private BotPanel()
    {
        super(false);
        setLayout(new BorderLayout());
        setBackground(ColorScheme.DARK_GRAY_COLOR);
    }

    /**
     * Associates the bot plugin panel with the script panel. Since the Bot Plugin has access to the injector the instance
     * of RuneLite must be passed forward through the scriptPanel constructor for script selection to work.
     *
     * @param accountPanel	The account panel to associate with the bot plugin panel.
     * @param scriptPanel	The script panel to associate with the bot plugin panel.
     */
    protected void associateBot(AccountPanel accountPanel, ScriptPanel scriptPanel) {


        Accordion accordion = new Accordion();

        JPanel generalPanel = GeneralPanel.getInstance();
        JPanel debugPanel = new DebugPanel();
        JPanel botViewPanel = new BotViewPanel();

        accordion.addBar("General", generalPanel);
        accordion.addBar("Debug", debugPanel);
        accordion.addBar("Bot View", botViewPanel);

        MaterialTab baseTab = new MaterialTab("Settings", tabGroup, accordion);
        MaterialTab  accountTab = new MaterialTab("Accounts", tabGroup, accountPanel);
        JScrollPane botPanelScrollPane = new JScrollPane(scriptPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        MaterialTab scriptTab = new MaterialTab("Scripts", tabGroup, botPanelScrollPane);


        tabGroup.setBorder(new EmptyBorder(5, 0, 0, 0));
        tabGroup.addTab(baseTab);
        tabGroup.addTab(accountTab);
        tabGroup.addTab(scriptTab);
        tabGroup.select(baseTab);

        add(tabGroup, BorderLayout.NORTH);
        add(display, BorderLayout.CENTER);
    }
}
