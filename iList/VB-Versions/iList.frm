VERSION 5.00
Begin VB.Form iList 
   Caption         =   "iList"
   ClientHeight    =   8415
   ClientLeft      =   120
   ClientTop       =   465
   ClientWidth     =   5145
   ControlBox      =   0   'False
   Icon            =   "iList.frx":0000
   LinkTopic       =   "Form1"
   MaxButton       =   0   'False
   MinButton       =   0   'False
   ScaleHeight     =   8415
   ScaleWidth      =   5145
   StartUpPosition =   3  '����ȱʡ
   Begin VB.CommandButton Command_sig 
      Caption         =   "Hourly Time Signal"
      Height          =   495
      Left            =   200
      TabIndex        =   32
      Top             =   720
      Width           =   1926
   End
   Begin VB.CommandButton Command_Q 
      Caption         =   "Quit"
      Height          =   495
      Left            =   120
      TabIndex        =   31
      Top             =   7800
      Width           =   1215
   End
   Begin VB.CommandButton Command_d7 
      Caption         =   "Done!"
      Enabled         =   0   'False
      Height          =   615
      Left            =   3600
      TabIndex        =   29
      Top             =   6480
      Visible         =   0   'False
      Width           =   700
   End
   Begin VB.CommandButton Command_d6 
      Caption         =   "Done!"
      Enabled         =   0   'False
      Height          =   615
      Left            =   3600
      TabIndex        =   28
      Top             =   5640
      Visible         =   0   'False
      Width           =   700
   End
   Begin VB.CommandButton Command_d5 
      Caption         =   "Done!"
      Enabled         =   0   'False
      Height          =   615
      Left            =   3600
      TabIndex        =   27
      Top             =   4800
      Visible         =   0   'False
      Width           =   700
   End
   Begin VB.CommandButton Command_d4 
      Caption         =   "Done!"
      Enabled         =   0   'False
      Height          =   615
      Left            =   3600
      TabIndex        =   26
      Top             =   3960
      Visible         =   0   'False
      Width           =   700
   End
   Begin VB.CommandButton Command_d3 
      Caption         =   "Done!"
      Enabled         =   0   'False
      Height          =   615
      Left            =   3600
      TabIndex        =   25
      Top             =   3120
      Visible         =   0   'False
      Width           =   700
   End
   Begin VB.CommandButton Command_d2 
      Caption         =   "Done!"
      Enabled         =   0   'False
      Height          =   615
      Left            =   3600
      TabIndex        =   24
      Top             =   2280
      Visible         =   0   'False
      Width           =   700
   End
   Begin VB.CommandButton Command_d1 
      Caption         =   "Done!"
      Enabled         =   0   'False
      Height          =   615
      Left            =   3600
      TabIndex        =   23
      Top             =   1440
      Visible         =   0   'False
      Width           =   700
   End
   Begin VB.CommandButton Command_im7 
      Caption         =   "Input"
      Height          =   615
      Left            =   4300
      TabIndex        =   15
      Top             =   6480
      Width           =   760
   End
   Begin VB.CommandButton Command_im6 
      Caption         =   "Input"
      Height          =   615
      Left            =   4300
      TabIndex        =   14
      Top             =   5640
      Width           =   760
   End
   Begin VB.CommandButton Command_im5 
      Caption         =   "Input"
      Height          =   615
      Left            =   4300
      TabIndex        =   13
      Top             =   4800
      Width           =   760
   End
   Begin VB.CommandButton Command_im4 
      Caption         =   "Input"
      Height          =   615
      Left            =   4300
      TabIndex        =   12
      Top             =   3960
      Width           =   760
   End
   Begin VB.CommandButton Command_im3 
      Caption         =   "Input"
      Height          =   615
      Left            =   4300
      TabIndex        =   11
      Top             =   3120
      Width           =   760
   End
   Begin VB.CommandButton Command_im2 
      Caption         =   "Input"
      Height          =   615
      Left            =   4300
      TabIndex        =   10
      Top             =   2280
      Width           =   760
   End
   Begin VB.CommandButton Command_im1 
      Caption         =   "Input"
      Height          =   615
      Left            =   4300
      TabIndex        =   9
      Top             =   1440
      Width           =   760
   End
   Begin VB.Timer Timer1 
      Interval        =   500
      Left            =   0
      Top             =   0
   End
   Begin VB.Label Label_A 
      Caption         =   "Powered by Pyromoea"
      BeginProperty Font 
         Name            =   "����"
         Size            =   9
         Charset         =   134
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   300
      Left            =   3250
      TabIndex        =   30
      Top             =   8040
      Width           =   1800
   End
   Begin VB.Label Label_t7 
      Caption         =   "Please initiate your seventh task!"
      Height          =   495
      Left            =   480
      TabIndex        =   22
      Top             =   6640
      Width           =   3100
   End
   Begin VB.Label Label_t6 
      Caption         =   "Please initiate your sixth task!"
      Height          =   300
      Left            =   480
      TabIndex        =   21
      Top             =   5800
      Width           =   3100
   End
   Begin VB.Label Label_t5 
      Caption         =   "Please initiate your fifth task!"
      Height          =   300
      Left            =   480
      TabIndex        =   20
      Top             =   4960
      Width           =   3100
   End
   Begin VB.Label Label_t4 
      Caption         =   "Please initiate your forth task!"
      Height          =   300
      Left            =   480
      TabIndex        =   19
      Top             =   4120
      Width           =   3100
   End
   Begin VB.Label Label_t3 
      Caption         =   "Please initiate your third task!"
      Height          =   300
      Left            =   480
      TabIndex        =   18
      Top             =   3280
      Width           =   3100
   End
   Begin VB.Label Label_t2 
      Caption         =   "Please initiate your second task!"
      Height          =   300
      Left            =   480
      TabIndex        =   17
      Top             =   2440
      Width           =   3100
   End
   Begin VB.Label Label_t1 
      Caption         =   "Please initiate your first task!"
      Height          =   300
      Left            =   480
      TabIndex        =   16
      Top             =   1600
      Width           =   3100
   End
   Begin VB.Label Label8 
      Caption         =   "    7"
      Height          =   615
      Left            =   120
      TabIndex        =   8
      Top             =   6480
      Width           =   255
   End
   Begin VB.Label Label7 
      Caption         =   "    6"
      Height          =   615
      Left            =   120
      TabIndex        =   7
      Top             =   5640
      Width           =   255
   End
   Begin VB.Label Label6 
      Caption         =   "    5"
      Height          =   615
      Left            =   120
      TabIndex        =   6
      Top             =   4800
      Width           =   255
   End
   Begin VB.Label Label5 
      Caption         =   "    4"
      Height          =   615
      Left            =   120
      TabIndex        =   5
      Top             =   3960
      Width           =   255
   End
   Begin VB.Label Label4 
      Caption         =   "    3"
      Height          =   615
      Left            =   120
      TabIndex        =   4
      Top             =   3120
      Width           =   255
   End
   Begin VB.Label Label3 
      Caption         =   "    2"
      Height          =   615
      Left            =   120
      TabIndex        =   3
      Top             =   2280
      Width           =   255
   End
   Begin VB.Label Label2 
      Caption         =   "    1"
      Height          =   615
      Left            =   120
      TabIndex        =   2
      Top             =   1440
      Width           =   255
   End
   Begin VB.Label Label1 
      Caption         =   "Time��"
      Height          =   495
      Left            =   1080
      TabIndex        =   1
      Top             =   60
      Width           =   975
   End
   Begin VB.Label Label_t 
      Height          =   540
      Left            =   2160
      TabIndex        =   0
      Top             =   60
      Width           =   2250
   End
End
Attribute VB_Name = "iList"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim cnt As Integer, clc As Boolean, sig As Boolean

Private Sub Command_Q_Click()
    End
End Sub

Private Sub Command_sig_Click()
    If sig = False Then
        If MsgBox("Are you sure to turn on the hourly time signal?", vbYesNo) = vbYes Then
            sig = True
            Command_sig.Caption = "Disable SIG"
        
        End If
    Else
        If MsgBox("Are you sure to turn off the hourly time signal?", vbYesNo) = vbYes Then
            sig = False
            Command_sig.Caption = "Hourly Time Signal"
        End If
    End If
End Sub

Private Sub Form_Load()
    cnt = 0
    sig = False

End Sub
Private Sub Command_d1_Click()
    Command_d1.Enabled = False
    Command_im1.Enabled = False
    cnt = cnt + 1
    If cnt = 1 Then
        MsgBox ("First blood!"), 0, "iList"
    ElseIf cnt = 2 Then
        MsgBox ("Double kill!"), 0, "iList"
    ElseIf cnt = 3 Then
        MsgBox ("Triple kill!"), 0, "iList"
    ElseIf cnt = 4 Then
        MsgBox ("Quadra kill!"), 0, "iList"
    ElseIf cnt = 5 Then
        MsgBox ("Penta kill!"), 0, "iList"
    ElseIf cnt = 6 Then
        MsgBox ("Hexa kill!"), 0, "iList"
    ElseIf cnt = 7 Then
        MsgBox ("ACE!"), 0, "iList"
    End If
End Sub

Private Sub Command_d2_Click()
    Command_d2.Enabled = False
    Command_im2.Enabled = False
    cnt = cnt + 1
    If cnt = 1 Then
        MsgBox ("First blood!"), 0, "iList"
    ElseIf cnt = 2 Then
        MsgBox ("Double kill!"), 0, "iList"
    ElseIf cnt = 3 Then
        MsgBox ("Triple kill!"), 0, "iList"
    ElseIf cnt = 4 Then
        MsgBox ("Quadra kill!"), 0, "iList"
    ElseIf cnt = 5 Then
        MsgBox ("Penta kill!"), 0, "iList"
    ElseIf cnt = 6 Then
        MsgBox ("Hexa kill!"), 0, "iList"
    ElseIf cnt = 7 Then
        MsgBox ("ACE!"), 0, "iList"
    End If
End Sub

Private Sub Command_d3_Click()
    Command_d3.Enabled = False
    Command_im3.Enabled = False
    cnt = cnt + 1
    If cnt = 1 Then
        MsgBox ("First blood!"), 0, "iList"
    ElseIf cnt = 2 Then
        MsgBox ("Double kill!"), 0, "iList"
    ElseIf cnt = 3 Then
        MsgBox ("Triple kill!"), 0, "iList"
    ElseIf cnt = 4 Then
        MsgBox ("Quadra kill!"), 0, "iList"
    ElseIf cnt = 5 Then
        MsgBox ("Penta kill!"), 0, "iList"
    ElseIf cnt = 6 Then
        MsgBox ("Hexa kill!"), 0, "iList"
    ElseIf cnt = 7 Then
        MsgBox ("ACE!"), 0, "iList"
    End If
End Sub

Private Sub Command_d4_Click()
    Command_d4.Enabled = False
    Command_im4.Enabled = False
    cnt = cnt + 1
    If cnt = 1 Then
        MsgBox ("First blood!"), 0, "iList"
    ElseIf cnt = 2 Then
        MsgBox ("Double kill!"), 0, "iList"
    ElseIf cnt = 3 Then
        MsgBox ("Triple kill!"), 0, "iList"
    ElseIf cnt = 4 Then
        MsgBox ("Quadra kill!"), 0, "iList"
    ElseIf cnt = 5 Then
        MsgBox ("Penta kill!"), 0, "iList"
    ElseIf cnt = 6 Then
        MsgBox ("Hexa kill!"), 0, "iList"
    ElseIf cnt = 7 Then
        MsgBox ("ACE!"), 0, "iList"
    End If
End Sub

Private Sub Command_d5_Click()
    Command_d5.Enabled = False
    Command_im5.Enabled = False
    cnt = cnt + 1
    If cnt = 1 Then
        MsgBox ("First blood!"), 0, "iList"
    ElseIf cnt = 2 Then
        MsgBox ("Double kill!"), 0, "iList"
    ElseIf cnt = 3 Then
        MsgBox ("Triple kill!"), 0, "iList"
    ElseIf cnt = 4 Then
        MsgBox ("Quadra kill!"), 0, "iList"
    ElseIf cnt = 5 Then
        MsgBox ("Penta kill!"), 0, "iList"
    ElseIf cnt = 6 Then
        MsgBox ("Hexa kill!"), 0, "iList"
    ElseIf cnt = 7 Then
        MsgBox ("ACE!"), 0, "iList"
    End If
End Sub

Private Sub Command_d6_Click()
    Command_d6.Enabled = False
    Command_im6.Enabled = False
    cnt = cnt + 1
    If cnt = 1 Then
        MsgBox ("First blood!"), 0, "iList"
    ElseIf cnt = 2 Then
        MsgBox ("Double kill!"), 0, "iList"
    ElseIf cnt = 3 Then
        MsgBox ("Triple kill!"), 0, "iList"
    ElseIf cnt = 4 Then
        MsgBox ("Quadra kill!"), 0, "iList"
    ElseIf cnt = 5 Then
        MsgBox ("Penta kill!"), 0, "iList"
    ElseIf cnt = 6 Then
        MsgBox ("Hexa kill!"), 0, "iList"
    ElseIf cnt = 7 Then
        MsgBox ("ACE!"), 0, "iList"
    End If
End Sub

Private Sub Command_d7_Click()
    Command_d7.Enabled = False
    Command_im7.Enabled = False
    cnt = cnt + 1
    If cnt = 1 Then
        MsgBox ("First blood!"), 0, "iList"
    ElseIf cnt = 2 Then
        MsgBox ("Double kill!"), 0, "iList"
    ElseIf cnt = 3 Then
        MsgBox ("Triple kill!"), 0, "iList"
    ElseIf cnt = 4 Then
        MsgBox ("Quadra kill!"), 0, "iList"
    ElseIf cnt = 5 Then
        MsgBox ("Penta kill!"), 0, "iList"
    ElseIf cnt = 6 Then
        MsgBox ("Hexa kill!"), 0, "iList"
    ElseIf cnt = 7 Then
        MsgBox ("ACE!"), 0, "iList"
    End If
End Sub

Private Sub Command_im1_Click()
    Label_t1.Caption = InputBox("Please input your task here:")
    Command_im1.Caption = "Modify"
    Command_d1.Enabled = True
    Command_d1.Visible = True

End Sub

Private Sub Command_im2_Click()
    Label_t2.Caption = InputBox("Please input your task here:")
    Command_im2.Caption = "Modify"
    Command_d2.Enabled = True
    Command_d2.Visible = True
End Sub

Private Sub Command_im3_Click()
    Label_t3.Caption = InputBox("Please input your task here:")
    Command_im3.Caption = "Modify"
    Command_d3.Enabled = True
    Command_d3.Visible = True
End Sub

Private Sub Command_im4_Click()
    Label_t4.Caption = InputBox("Please input your task here:")
    Command_im4.Caption = "Modify"
    Command_d4.Enabled = True
    Command_d4.Visible = True
End Sub

Private Sub Command_im5_Click()
    Label_t5.Caption = InputBox("Please input your task here:")
    Command_im5.Caption = "Modify"
    Command_d5.Enabled = True
    Command_d5.Visible = True
End Sub

Private Sub Command_im6_Click()
    Label_t6.Caption = InputBox("Please input your task here:")
    Command_im6.Caption = "Modify"
    Command_d6.Enabled = True
    Command_d6.Visible = True
End Sub

Private Sub Command_im7_Click()
    Label_t7.Caption = InputBox("Please input your task here:")
    Command_im7.Caption = "Modify"
    Command_d7.Enabled = True
    Command_d7.Visible = True
End Sub

Private Sub Label_A_DblClick()
    MsgBox ("iList v1.0.4") & vbCrLf & ("https://github.com/phlzy/iList") & vbCrLf & ("�������ܣ����㱨ʱ"), 0, "iList"
End Sub

Private Sub Timer1_Timer()
    Label_t.Caption = Time
    If sig = True Then
        If Time = #1:00:00 AM# Then
            MsgBox ("1���ˣ���˯��"), 0
        ElseIf Time = #2:00:00 AM# Then
            MsgBox ("̫���ˣ����Ҫ˯��"), 0
        ElseIf Time = #3:00:00 AM# Then
            MsgBox ("����...ʧ������"), 0
        ElseIf Time = #4:00:00 AM# Then
            MsgBox ("������賿4���iList��"), 0
        ElseIf Time = #5:00:00 AM# Then
            MsgBox ("����ĳ��ӱ���ԣ����ڲ�5����"), 0
        ElseIf Time = #6:00:00 AM# Then
            MsgBox ("��6��Ϳ�ʼ�����ˣ�һ������ҵ�������˰�"), 0
        ElseIf Time = #7:00:00 AM# Then
            MsgBox ("7���ˣ����緹����"), 0
        ElseIf Time = #8:00:00 AM# Then
            MsgBox ("������8�������������п���"), 0
        ElseIf Time = #9:00:00 AM# Then
            MsgBox ("�Ѿ�9����"), 0
        ElseIf Time = #10:00:00 AM# Then
            MsgBox ("�Ѿ�10���ˣ���Ҫ�¿���..."), 0
        ElseIf Time = #11:00:00 AM# Then
            MsgBox ("���Ӷ�����11������Ŷ"), 0
        ElseIf Time = #12:00:00 AM# Then
            MsgBox ("�����ȥ�ˣ����緹����"), 0
        ElseIf Time = #1:00:00 PM# Then
            MsgBox ("����1�㣬��΢��Ϣһ��"), 0
        ElseIf Time = #2:00:00 PM# Then
            MsgBox ("ʱ�������죬�Ѿ�����2����"), 0
        ElseIf Time = #3:00:00 PM# Then
            MsgBox ("������ɶ����ˣ�ʱ�䲻����qwq"), 0
        ElseIf Time = #4:00:00 PM# Then
            MsgBox ("����Ҳ��Ҫ������"), 0
        ElseIf Time = #5:00:00 PM# Then
            MsgBox ("�ǲ��Ǻ�������ˣ�5����Ŷ"), 0
        ElseIf Time = #6:00:00 PM# Then
            MsgBox ("����������Ϣһ��"), 0
        ElseIf Time = #7:00:00 PM# Then
            MsgBox ("һ��æµ��ҹ���ǲ����Ѿ���ʼ����"), 0
        ElseIf Time = #8:00:00 PM# Then
            MsgBox ("��һ��Сʱ��ȥ�ˣ�������8����"), 0
        ElseIf Time = #9:00:00 PM# Then
            MsgBox ("9���ˣ��ǲ��ǿ�ʼ������"), 0
        ElseIf Time = #10:00:00 PM# Then
            MsgBox ("10����������Ļ��������"), 0
        ElseIf Time = #11:00:00 PM# Then
            MsgBox ("11���ˣ������²���Ҫͨ��"), 0
        ElseIf Time = #12:00:00 PM# Then
            MsgBox ("�µ�һ�쿪ʼ�ˣ��������ҵ��������"), 0
        End If
    End If
End Sub
