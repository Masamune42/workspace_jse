USE [Ecole]
GO
/****** Object:  Table [dbo].[eleves]    Script Date: 05/29/2016 22:20:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[eleves](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nom] [nvarchar](50) NULL,
	[prenom] [nvarchar](50) NULL,
	[adresse] [nvarchar](100) NULL,
 CONSTRAINT [pk_eleves] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[eleves] ON
INSERT [dbo].[eleves] ([id], [nom], [prenom], [adresse]) VALUES (1, N'Dupont', N'Jean', N'10 impasse des Lilas - 79000 NIORT')
INSERT [dbo].[eleves] ([id], [nom], [prenom], [adresse]) VALUES (2, N'Durand', N'Sophie', N'40 rue des Rossignoles - 44000 NANTES')
SET IDENTITY_INSERT [dbo].[eleves] OFF
