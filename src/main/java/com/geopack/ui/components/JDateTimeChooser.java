package com.geopack.ui.components;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class JDateTimeChooser extends JPanel implements ActionListener,
                                                        PropertyChangeListener {
    protected static final String DAY_CHANGED_PROPERTY = "day_changed";

    protected IDateEditor dateEditor;

    protected JButton calendarButton;

    protected DateTimePanel jcalendar;

    protected JPopupMenu popup;

    protected boolean isInitialized;

    protected boolean dateSelected;

    protected Date lastSelectedDate;

    private ChangeListener changeListener;


    /**
     * Creates a new JDateTimeChooser. By default, no date is set and the textfield
     * is empty.
     */
    public JDateTimeChooser(boolean showTime) {
        this(null, null, null, null, showTime);
    }

    /**
     * Creates a new JDateTimeChooser with given IDateEditor.
     *
     * @param dateEditor the dateEditor to be used used to display the date. if null, a
     *                   JTextFieldDateEditor is used.
     */
    public JDateTimeChooser(IDateEditor dateEditor, boolean showTime) {
        this(null, null, null, dateEditor, showTime);
    }

    /**
     * Creates a new JDateTimeChooser.
     *
     * @param date the date or null
     */
    public JDateTimeChooser(Date date, boolean showTime) {
        this(date, null, showTime);
    }

    /**
     * Creates a new JDateTimeChooser.
     *
     * @param date             the date or null
     * @param dateFormatString the date format string or null (then MEDIUM SimpleDateFormat
     *                         format is used)
     */
    public JDateTimeChooser(Date date, String dateFormatString, boolean showTime) {
        this(date, dateFormatString, null, showTime);
    }

    /**
     * Creates a new JDateTimeChooser.
     *
     * @param date             the date or null
     * @param dateFormatString the date format string or null (then MEDIUM SimpleDateFormat
     *                         format is used)
     * @param dateEditor       the dateEditor to be used used to display the date. if null, a
     *                         JTextFieldDateEditor is used.
     */
    public JDateTimeChooser(Date date, String dateFormatString,
                            IDateEditor dateEditor, boolean showTime) {
        this(null, date, dateFormatString, dateEditor, showTime);
    }

    /**
     * Creates a new JDateTimeChooser. If the JDateTimeChooser is created with this
     * constructor, the mask will be always visible in the date editor. Please
     * note that the date pattern and the mask will not be changed if the locale
     * of the JDateTimeChooser is changed.
     *
     * @param datePattern the date pattern, e.g. "MM/dd/yy"
     * @param maskPattern the mask pattern, e.g. "##/##/##"
     * @param placeholder the placeholer charachter, e.g. '_'
     */
    public JDateTimeChooser(String datePattern, String maskPattern, char placeholder, boolean showTime) {
        this(null, null, datePattern, new JTextFieldDateEditor(datePattern,
                maskPattern, placeholder), showTime);
    }

    /**
     * Creates a new JDateTimeChooser.
     *
     * @param jcal             the JCalendar to be used
     * @param date             the date or null
     * @param dateFormatString the date format string or null (then MEDIUM Date format is
     *                         used)
     * @param dateEditor       the dateEditor to be used used to display the date. if null, a
     *                         JTextFieldDateEditor is used.
     */
    public JDateTimeChooser(DateTimePanel jcal, Date date, String dateFormatString,
                            IDateEditor dateEditor, boolean showTime) {
        setName("JDateTimeChooser");

        this.dateEditor = dateEditor;
        if (this.dateEditor == null) {
            this.dateEditor = new JTextFieldDateEditor();
        }
        this.dateEditor.addPropertyChangeListener("date", this);

        if (jcal == null) {
            jcalendar = new DateTimePanel(date, showTime);
        } else {
            jcalendar = jcal;
            if (date != null) {
                jcalendar.setDate(date);
            }
        }

        setLayout(new BorderLayout());

        jcalendar.getDayChooser().addPropertyChangeListener("day", this);
        // always fire"day" property even if the user selects
        // the already selected day again
        jcalendar.getDayChooser().setAlwaysFireDayProperty(true);

        setDateFormatString(dateFormatString);
        setDate(date);

        // Display a calendar button with an icon
        URL iconURL = getClass().getResource(
                "/com/toedter/calendar/images/JDateChooserIcon.gif");
        ImageIcon icon = new ImageIcon(iconURL);

        calendarButton = new JButton(icon) {
            private static final long serialVersionUID = -1913767779079949668L;

            public boolean isFocusable() {
                return false;
            }
        };
        calendarButton.setMargin(new Insets(0, 0, 0, 0));
        calendarButton.addActionListener(this);

        // Alt + 'C' selects the calendar.
        calendarButton.setMnemonic(KeyEvent.VK_C);

        add(calendarButton, BorderLayout.EAST);
        add(this.dateEditor.getUiComponent(), BorderLayout.CENTER);

        calendarButton.setMargin(new Insets(0, 0, 0, 0));
        // calendarButton.addFocusListener(this);

        popup = new JPopupMenu() {
            private static final long serialVersionUID = -6078272560337577761L;

            public void setVisible(boolean b) {
                Boolean isCanceled = (Boolean) getClientProperty("JPopupMenu.firePopupMenuCanceled");
                if (b
                        || (!b && dateSelected)
                        || ((isCanceled != null) && !b && isCanceled
                        .booleanValue())) {
                    super.setVisible(b);
                }
            }
        };

        popup.setLightWeightPopupEnabled(true);

        popup.add(jcalendar);

        lastSelectedDate = date;

        // Corrects a problem that occured when the JMonthChooser's combobox is
        // displayed, and a click outside the popup does not close it.

        // The following idea was originally provided by forum user
        // podiatanapraia:
        changeListener = new ChangeListener() {
            boolean hasListened = false;

            public void stateChanged(ChangeEvent e) {
                if (hasListened) {
                    hasListened = false;
                    return;
                }
                if (popup.isVisible()
                        && JDateTimeChooser.this.jcalendar.getMonthChooser()
                        .getComboBox().hasFocus()) {
                    MenuElement[] me = MenuSelectionManager.defaultManager()
                            .getSelectedPath();
                    MenuElement[] newMe = new MenuElement[me.length + 1];
                    newMe[0] = popup;
                    for (int i = 0; i < me.length; i++) {
                        newMe[i + 1] = me[i];
                    }
                    hasListened = true;
                    MenuSelectionManager.defaultManager()
                            .setSelectedPath(newMe);
                }
            }
        };
        MenuSelectionManager.defaultManager().addChangeListener(changeListener);
        // end of code provided by forum user podiatanapraia

        isInitialized = true;
    }

    /**
     * Called when the jalendar button was pressed.
     *
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
        int x = calendarButton.getWidth()
                - (int) popup.getPreferredSize().getWidth();
        int y = calendarButton.getY() + calendarButton.getHeight();

        Calendar calendar = Calendar.getInstance();
        Date date = dateEditor.getDate();
        if (date != null) {
            calendar.setTime(date);
        }
        jcalendar.setCalendar(calendar);
        popup.show(calendarButton, x, y);
        dateSelected = false;
    }

    /**
     * Listens for a "date" property change or a "day" property change event
     * from the JCalendar. Updates the date editor and closes the popup.
     *
     * @param evt the event
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("day")) {
            if (popup.isVisible()) {
                dateSelected = true;
                popup.setVisible(false);
                setDate(jcalendar.getCalendar().getTime());
                firePropertyChange(DAY_CHANGED_PROPERTY, false, true);
            }
        } else if (evt.getPropertyName().equals("date")) {
            if (evt.getSource() == dateEditor) {
                firePropertyChange("date", evt.getOldValue(), evt.getNewValue());
            } else {
                setDate((Date) evt.getNewValue());
            }
        }
    }

    /**
     * Updates the UI of itself and the popup.
     */
    public void updateUI() {
        super.updateUI();
        setEnabled(isEnabled());

        if (jcalendar != null) {
            SwingUtilities.updateComponentTreeUI(popup);
        }
    }

    /**
     * Sets the locale.
     *
     * @param l The new locale value
     */
    public void setLocale(Locale l) {
        super.setLocale(l);
        dateEditor.setLocale(l);
        jcalendar.setLocale(l);
    }

    /**
     * Gets the date format string.
     *
     * @return Returns the dateFormatString.
     */
    public String getDateFormatString() {
        return dateEditor.getDateFormatString();
    }

    /**
     * Sets the date format string. E.g "MMMMM d, yyyy" will result in "July 21,
     * 2004" if this is the selected date and locale is English.
     *
     * @param dfString The dateFormatString to set.
     */
    public void setDateFormatString(String dfString) {
        dateEditor.setDateFormatString(dfString);
        invalidate();
    }

    /**
     * Returns the date. If the JDateTimeChooser is started with a null date and no
     * date was set by the user, null is returned.
     *
     * @return the current date
     */
    public Date getDate() {
        return dateEditor.getDate();
    }

    /**
     * Sets the date. Fires the property change "date" if date != null.
     *
     * @param date the new date.
     */
    public void setDate(Date date) {
        dateEditor.setDate(date);
        if (getParent() != null) {
            getParent().invalidate();
        }
    }

    /**
     * Returns the calendar. If the JDateTimeChooser is started with a null date (or
     * null calendar) and no date was set by the user, null is returned.
     *
     * @return the current calendar
     */
    public Calendar getCalendar() {
        Date date = getDate();
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Sets the calendar. Value null will set the null date on the date editor.
     *
     * @param calendar the calendar.
     */
    public void setCalendar(Calendar calendar) {
        if (calendar == null) {
            dateEditor.setDate(null);
        } else {
            dateEditor.setDate(calendar.getTime());
        }
    }

    /**
     * Enable or disable the JDateTimeChooser.
     *
     * @param enabled the new enabled value
     */
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (dateEditor != null) {
            dateEditor.setEnabled(enabled);
            calendarButton.setEnabled(enabled);
            if (!enabled && dateEditor.getUiComponent() instanceof JTextComponent)
                ((JTextComponent) dateEditor.getUiComponent()).setDisabledTextColor(Color.BLACK);
        }
    }

    /**
     * Returns true, if enabled.
     *
     * @return true, if enabled.
     */
    public boolean isEnabled() {
        return super.isEnabled();
    }

    /**
     * Sets the icon of the buuton.
     *
     * @param icon The new icon
     */
    public void setIcon(ImageIcon icon) {
        calendarButton.setIcon(icon);
    }

    /**
     * Sets the font of all subcomponents.
     *
     * @param font the new font
     */
    public void setFont(Font font) {
        if (isInitialized) {
            dateEditor.getUiComponent().setFont(font);
            jcalendar.setFont(font);
        }
        super.setFont(font);
    }

    /**
     * Returns the JCalendar component. THis is usefull if you want to set some
     * properties.
     *
     * @return the JCalendar
     */
    public DateTimePanel getJCalendar() {
        return jcalendar;
    }

    /**
     * Returns the calendar button.
     *
     * @return the calendar button
     */
    public JButton getCalendarButton() {
        return calendarButton;
    }

    /**
     * Returns the date editor.
     *
     * @return the date editor
     */
    public IDateEditor getDateEditor() {
        return dateEditor;
    }

    /**
     * Sets a valid date range for selectable dates. If max is before min, the
     * default range with no limitation is set.
     *
     * @param min the minimum selectable date or null (then the minimum date is
     *            set to 01\01\0001)
     * @param max the maximum selectable date or null (then the maximum date is
     *            set to 01\01\9999)
     */
    public void setSelectableDateRange(Date min, Date max) {
        jcalendar.setSelectableDateRange(min, max);
        dateEditor.setSelectableDateRange(jcalendar.getMinSelectableDate(),
                jcalendar.getMaxSelectableDate());
    }

    public void setMaxSelectableDate(Date max) {
        jcalendar.setMaxSelectableDate(max);
        dateEditor.setMaxSelectableDate(max);
    }

    public void setMinSelectableDate(Date min) {
        jcalendar.setMinSelectableDate(min);
        dateEditor.setMinSelectableDate(min);
    }

    /**
     * Gets the maximum selectable date.
     *
     * @return the maximum selectable date
     */
    public Date getMaxSelectableDate() {
        return jcalendar.getMaxSelectableDate();
    }

    /**
     * Gets the minimum selectable date.
     *
     * @return the minimum selectable date
     */
    public Date getMinSelectableDate() {
        return jcalendar.getMinSelectableDate();
    }

    /**
     * Should only be invoked if the JDateTimeChooser is not used anymore. Due to popup
     * handling it had to register a change listener to the default menu
     * selection manager which will be unregistered here. Use this method to
     * cleanup possible memory leaks.
     */
    public void cleanup() {
        MenuSelectionManager.defaultManager().removeChangeListener(changeListener);
        changeListener = null;
    }

    /**
     * Creates a JFrame with a JDateTimeChooser inside and can be used for testing.
     *
     * @param s The command line arguments
     */
    public static void main(String[] s) {
        JFrame frame = new JFrame("JDateTimeChooser");
        JDateTimeChooser dateChooser = new JDateTimeChooser(new Date(), "dd.MM.yyyy HH:mm:ss", true);
        frame.getContentPane().add(dateChooser);
        frame.pack();
        frame.setVisible(true);
    }

}
