package com.geopack.ui.components;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.components.JSpinField;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;


/**
 * Date: 05.09.2006
 * Time: 12:33:51
 */
public class DateTimePanel extends JPanel
        implements PropertyChangeListener {

    protected transient ChangeEvent changeEvent = null;
    protected EventListenerList lList = new EventListenerList();

    protected Date initialDate = null;

    // chooser components
    protected JCalendar calendar;
    protected JSpinField hours;
    protected JSpinField mins;
    protected JSpinField secs;
    protected JPanel previewPanel;

    protected static final Insets BREATH_BOTTOM = new Insets(0, 0, 10, 0);

    /**
     * **********************************************************************
     * Creates a date-time chooser with the curent date as an initial date.
     * ***********************************************************************
     */
    public DateTimePanel(boolean showTime) {
        this(null, showTime);
    }

    /**
     * **********************************************************************
     * Creates a date-time chooser with the given date as an initial date.
     * ***********************************************************************
     */
    public DateTimePanel(Date initialDate, boolean showTime) {
        this.initialDate = initialDate;
        createItself(showTime);
    }

    /**
     * **********************************************************************
     * Creates this component...
     * ***********************************************************************
     */
    protected void createItself(boolean showTime) {
        setLayout(new GridBagLayout());

        JButton now = new JButton("Текущее");
        now.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DateTimePanel.this.setDate(new Date());
            }
        });
        JButton zero = new JButton("Обнулить время");
        zero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hours.setValue(0);
                mins.setValue(0);
                secs.setValue(0);
            }
        });
        JPanel buttons = new JPanel();
        buttons.add(now);
        buttons.add(zero);

        calendar = new JCalendar();
        calendar.setWeekOfYearVisible(false);
        calendar.addPropertyChangeListener(this);
        calendar.getYearChooser().addPropertyChangeListener(this);

        JLabel hoursLabel = new JLabel("час: ");
        hours = new JSpinField(0, 23);
        hours.adjustWidthToMaximumValue();
        hours.addPropertyChangeListener(this);

        JLabel minsLabel = new JLabel(" мин: ");
        mins = new JSpinField(0, 59);
        mins.adjustWidthToMaximumValue();
        mins.addPropertyChangeListener(this);

        JLabel secsLabel = new JLabel(" сек: ");
        secs = new JSpinField(0, 59);
        secs.adjustWidthToMaximumValue();
        secs.addPropertyChangeListener(this);

        setDate(initialDate == null ? new Date() : initialDate);
        GridBagConstraints gbc = new GridBagConstraints();
        // put it together
        int y = 0;
        ConstraintAndPut.doIt(calendar, this, 0, y++, 6, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0, gbc);
//     addComponent(this, calendar, 0, 0, 6, 1, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST, 0.0, 0.0);
        if (showTime) {
            ConstraintAndPut.doIt(hoursLabel, this, 0, y, 1, 1, 50.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, BREATH_BOTTOM, 0, 0, gbc);
//        addComponent(this, hoursLabel, 0, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST, 0.0, 0.0, BREATH_BOTTOM);

            ConstraintAndPut.doIt(hours, this, 1, y, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, BREATH_BOTTOM, 0, 0, gbc);
//        addComponent(this, hours, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST, 0.0, 0.0, BREATH_BOTTOM);

            ConstraintAndPut.doIt(minsLabel, this, 2, y, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, BREATH_BOTTOM, 0, 0, gbc);
//        addComponent(this, minsLabel, 2, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST, 0.0, 0.0, BREATH_BOTTOM);

            ConstraintAndPut.doIt(mins, this, 3, y, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, BREATH_BOTTOM, 0, 0, gbc);
//        addComponent(this, mins, 3, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST, 0.0, 0.0, BREATH_BOTTOM);

            ConstraintAndPut.doIt(secsLabel, this, 4, y, 1, 1, 1.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, BREATH_BOTTOM, 0, 0, gbc);
//        addComponent(this, secsLabel, 4, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST, 0.0, 0.0, BREATH_BOTTOM);

            ConstraintAndPut.doIt(secs, this, 5, y++, 1, 1, 50.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, BREATH_BOTTOM, 0, 0, gbc);
//        addComponent(this, secs, 5, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST, 0.0, 0.0, BREATH_BOTTOM);
        }
        ConstraintAndPut.doIt(buttons, this, 0, y++, 6, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, BREATH_BOTTOM, 0, 0, gbc);
//        addComponent(this, buttons, 0, 2, 6, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER, 0.0, 0.0, BREATH_BOTTOM);
    }

    /**
     * **********************************************************************
     * Gets the current value from the date-time chooser.
     * ***********************************************************************
     */
    public Date getDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar.getDate());
        cal.set(Calendar.HOUR_OF_DAY, hours.getValue());
        cal.set(Calendar.MINUTE, mins.getValue());
        cal.set(Calendar.SECOND, secs.getValue());
        return cal.getTime();
    }

    /**
     * **********************************************************************
     * Sets the given date as a new value for the date-time chooser. <p>
     *
     * @param newDate to be set into the chooser
     *                ***********************************************************************
     */
    public void setDate(Date newDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(newDate);
        hours.setValue(cal.get(Calendar.HOUR_OF_DAY));
        mins.setValue(cal.get(Calendar.MINUTE));
        secs.setValue(cal.get(Calendar.SECOND));
        calendar.setDate(newDate);
    }

    public JDayChooser getDayChooser() {
        return calendar.getDayChooser();
    }

    public JMonthChooser getMonthChooser() {
        return calendar.getMonthChooser();
    }

    public void setCalendar(Calendar c) {
        calendar.setCalendar(c);
        hours.setValue(c.get(Calendar.HOUR_OF_DAY));
        mins.setValue(c.get(Calendar.MINUTE));
        secs.setValue(c.get(Calendar.SECOND));
    }

    public Calendar getCalendar() {
        Calendar c = calendar.getCalendar();
        c.set(Calendar.HOUR_OF_DAY, hours.getValue());
        c.set(Calendar.MINUTE, mins.getValue());
        c.set(Calendar.SECOND, secs.getValue());
        return c;
    }

    public void setSelectableDateRange(Date min, Date max) {
        calendar.setSelectableDateRange(min, max);
    }

    public Date getMinSelectableDate() {
        return calendar.getMinSelectableDate();
    }

    public Date getMaxSelectableDate() {
        return calendar.getMaxSelectableDate();
    }

    public void setMaxSelectableDate(Date max) {
        calendar.setMaxSelectableDate(max);
    }

    public void setMinSelectableDate(Date min) {
        calendar.setMinSelectableDate(min);
    }

    /**
     * **********************************************************************
     * Returns a date that was used to initiate this date-time chooser
     * instance. It can be null.
     * ***********************************************************************
     */
    public Date getInitialDate() {
        return initialDate;
    }

    /**
     * **********************************************************************
     * Returns the preview panel that shows a currently chosen date. <p>
     *
     * @return the current preview panel
     *         ***********************************************************************
     */
    public JPanel getPreviewPanel() {
        return previewPanel;
    }

    /**
     * **********************************************************************
     * Sets the current preview panel. Usually a preview panel
     * implements <tt>ChangeListener</tt> interface and registers its
     * wish to be notified when a new date is selected in the
     * chooser. <p>
     *
     * @param preview is a panel used to display currently selected date
     *                ***********************************************************************
     */
    public void setPreviewPanel(JPanel preview) {
        if (previewPanel != null)
            remove(previewPanel);
        previewPanel = preview;
        if (previewPanel != null) {
            GridBagConstraints gbc = new GridBagConstraints();
            ConstraintAndPut.doIt(previewPanel, this, 0, 10, 10, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0, gbc);

//            addComponent(this, previewPanel, 0, 10, 10, 1, GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST, 1.0, 1.0);
        }
    }

    /**
     * **********************************************************************
     * Shows a modal date-time chooser dialog and blocks until the
     * dialog is hidden. The dialog has three buttons: OK, Empty,
     * Cancel. <p>
     * <p/>
     * If the user presses the "OK" button, then this method
     * hides/disposes the dialog and returns the selected date. <p>
     * <p/>
     * If the user presses the "Cancel" button or closes the dialog
     * without pressing "OK", then this method hides/disposes the
     * dialog and returns the initial date (which could have been
     * null). <p>
     * <p/>
     * If the user presses the "Empty" button, then this method
     * hides/disposes the dialog and returns null. It indicates that
     * no date is selected (even though that might have been a date as
     * an initial value). <p>
     *
     * @param parent      is the parent Component for the dialog
     * @param title       contains the dialog's title
     * @param initialDate is shown when the dialog starts; if this is
     *                    null the current date is shown
     * @return the selected date (if OK pressed), the initial date (if
     *         Cancel presed), or null (if Empty pressed)
     *         <p/>
     *         ***********************************************************************
     */
    public static Date showDialog(Component parent,
                                  String title,
                                  Date initialDate, boolean showTime) {
        return showDialog(parent, title, new DateTimePanel(initialDate, showTime));
    }

    /**
     * **********************************************************************
     * Shows a modal date-time chooser dialog and blocks until the
     * dialog is hidden. The dialog has three buttons: OK, Empty,
     * Cancel. See details how the buttons are dealt with in {@link
     * 
     * <p/>
     * This method allows to create an instance of a date-time chooser
     * separately, and perhaps to customize it (e.g. by calling
     * <tt>chooser.setPreviewPanel (myPreviewPanel)</tt>) before it is
     * used in a modal dialog. <p>
     *
     * @param parent  is the parent Component for the dialog
     * @param title   contains the dialog's title
     * @param chooser is the chooser instance that was created
     *                separately and will be used in this dialog
     * @return the selected date (if OK pressed), the initial date (if
     *         Cancel presed), or null (if Empty pressed)
     *         <p/>
     *         ***********************************************************************
     */
    public static Date showDialog(Component parent,
                                  String title,
                                  DateTimePanel chooser) {
        String[] buttons = new String[]{"OK", "Empty", "Cancel"};
        int selected =
                JOptionPane.showOptionDialog(parent,
                        chooser,
                        title,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        buttons,
                        null);
        if (selected == 0)
            return chooser.getDate();         // 'ok' selected
        else if (selected == 1)
            return null;                      // 'empty' selected
        else
            return chooser.getInitialDate();  // cancelled
    }

    /**
     * **********************************************************************
     * Adds a listener that is notified when a new date is
     * selected. <p>
     *
     * @param listener to be added
     *                 ***********************************************************************
     */
    public void addChangeListener(ChangeListener listener) {
        lList.add(ChangeListener.class, listener);
    }

    /**
     * **********************************************************************
     * Remove a listener that has been previously added by {@link
     * #addChangeListener}. <p>
     *
     * @param listener to be added
     *                 ***********************************************************************
     */
    public void removeChangeListener(ChangeListener listener) {
        lList.remove(ChangeListener.class, listener);
    }

    /**
     * **********************************************************************
     * Returns an array of all the <code>ChangeListener</code>s. <p>
     *
     * @return all of the <code>ChangeListener</code>s added, or an empty
     *         array if no listeners have been added
     *         ***********************************************************************
     */
    public ChangeListener[] getChangeListeners() {
        return (ChangeListener[]) lList
                .getListeners(ChangeListener.class);
    }

    /**
     * **********************************************************************
     * Runs each <code>ChangeListener</code>'s
     * <code>stateChanged</code> method. <p>
     *
     * @see EventListenerList
     *      ***********************************************************************
     */
    protected void fireStateChanged() {
        Object[] listeners = lList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
            }
        }
    }

    /**
     * **********************************************************************
     * Implementing PropertyChangeListener. This implementation only
     * propagates all property change events as a ChangeEvent.
     * ***********************************************************************
     */
    public void propertyChange(PropertyChangeEvent evt) {
        fireStateChanged();
    }


    public static void main(String[] args) {
        DateTimePanel chooser = new DateTimePanel(false);
        JFrame frame = new JFrame();
        frame.getContentPane().add(chooser);
        frame.pack();
        frame.setVisible(true);
    }

}
